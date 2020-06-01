package undertone.com.logging.service.fluentd;

import com.undertone.loggingservice.ImpressionSqsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import undertone.com.logging.dto.fluentd.ImpressionFluentdLog;
import undertone.com.logging.util.MonitorMetric;

import java.time.Instant;

@Service
@Slf4j
public class ImpressionLogService extends FluentdLogService<ImpressionFluentdLog> {

  @Value("${buffer.size.impression}")
  private int bufferSize;

  @Value("${fluentd.ext.impression}")
  private String extension;

  @Override
  protected int getBufferSize() {
    return bufferSize;
  }

  @Override
  protected String getExtension() {
    return extension;
  }

  @Override
  protected MonitorMetric getSentMonitorMetric() {
    return MonitorMetric.FLUENTD_SEND_IMPRESSION;
  }

  @Override
  protected ImpressionFluentdLog sqsRowToFluentdLog(String sqsRow) {

    ImpressionSqsData.Builder builder = ImpressionSqsData.newBuilder();
    updateBuilder(sqsRow, builder);
    ImpressionSqsData impressionSqsMessage = builder.build();

    return ImpressionFluentdLog.builder()
      .requestTime(formatEventCreationTime(getEventSendTime(impressionSqsMessage.getParamBucket())))
      .requestId(impressionSqsMessage.getRequestId())
      .imprTime(formatLogCreationTime(Instant.now()))
      .utid(impressionSqsMessage.getUtid())
      .priceTag(impressionSqsMessage.getPriceTag())
      .uri(impressionSqsMessage.getUri())
      .externalUserId(impressionSqsMessage.getExternalUserId())
      .zoneId(impressionSqsMessage.getZoneId())
      .bannerId(impressionSqsMessage.getBannerId())
      .build();
  }


}
