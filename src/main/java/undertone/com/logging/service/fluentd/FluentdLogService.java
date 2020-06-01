package undertone.com.logging.service.fluentd;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;
import undertone.com.logging.dto.SqsMessage;
import undertone.com.logging.dto.fluentd.FluentdLog;
import undertone.com.logging.util.MonitorMetric;
import undertone.com.logging.util.MonitorService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static undertone.com.logging.util.MonitorMetric.INVALID_DATA_FORMAT;

@Slf4j
public abstract class FluentdLogService<T extends FluentdLog> {

  private static final DateTimeFormatter LOG_CREATION_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
  private static final DateTimeFormatter EVENT_CREATION_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  @Autowired
  protected MonitorService monitorService;

  @Autowired
  private FluentdSendService fluentdSendService;

  private CsvSchema schema;

  private final List<T> logs = new ArrayList<>(1024);

  private final List<Acknowledgment> acknowledgments = new ArrayList<>(1024);

  abstract String getExtension();

  abstract int getBufferSize();

  abstract MonitorMetric getSentMonitorMetric();

  abstract T sqsRowToFluentdLog(String sqsRow);

  @PostConstruct
  @SuppressWarnings({"unchecked", "ConstantConditions"})
  public void initFluentdLogService() {

    Class<T> logClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), FluentdLogService.class);

    final CsvMapper mapper = new CsvMapper();

    schema = mapper.schemaFor(logClass)
      .withColumnSeparator('\t')
      .withNullValue("\\N")
      .withoutHeader()
      .withoutQuoteChar();

    log.info("csv schema for class {}: {}", logClass.getSimpleName(), schema);
  }

  @PreDestroy
  public void preDestroy() {
    sendToFluentD(logs);
  }

  public void logMessage(SqsMessage sqsMessage, Acknowledgment acknowledgment) {

    List<T> fluentdLogs = sqsMessage.getData().stream().map(this::sqsRowToFluentdLog).collect(Collectors.toList());

    synchronized (logs) {

      logs.addAll(fluentdLogs);
      acknowledgments.add(acknowledgment);

      if (logs.size() >= getBufferSize()) {
        final List<T> logsCopy = List.copyOf(logs);
        logs.clear();
        sendToFluentD(logsCopy);
      }
    }
  }

  LocalDateTime getEventSendTime(String paramBucket) {
    return StringUtils.isBlank(paramBucket) ?
      LocalDateTime.now() :
      LocalDateTime.ofInstant(Instant.ofEpochSecond((new BigInteger(paramBucket, 36)).longValue()), ZoneId.of("GMT"));
  }

  String formatEventCreationTime(LocalDateTime eventCreationTime) {
    return eventCreationTime.format(EVENT_CREATION_TIME_FORMAT);
  }

  String formatLogCreationTime(Instant instant) {
    return LOG_CREATION_TIME_FORMAT.withZone(ZoneId.of("GMT")).format(instant);
  }

  private void sendToFluentD(List<T> records) {

    CompletableFuture<HttpStatus> futureResponse = fluentdSendService.sendAsync(getExtension(), schema, getSentMonitorMetric(), records);

    futureResponse.thenAccept(httpStatus -> {
      if (httpStatus == HttpStatus.OK) {
        log.debug("Acknowledgment");
//      acknowledgments.forEach(Acknowledgment::acknowledge);
      }
    });
  }

  void updateBuilder(String sqsRow, Message.Builder builder) {
    String sqsDataType = builder.getDefaultInstanceForType().getClass().getSimpleName();

    try {
      JsonFormat.parser().ignoringUnknownFields().merge(sqsRow, builder);
    } catch (InvalidProtocolBufferException e) {
      log.error("Cannot convert line {} to {}", sqsRow, sqsDataType);
      monitorService.incrementCounter(INVALID_DATA_FORMAT);
      throw new IllegalArgumentException("Cannot convert line " + sqsRow + " to " + sqsDataType);
    }
  }

}
