package undertone.com.logging.service.fluentd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import undertone.com.logging.config.AsyncConfig;
import undertone.com.logging.config.LoggingServiceExceptionHandled;
import undertone.com.logging.dto.fluentd.FluentdLog;
import undertone.com.logging.dto.fluentd.FluentdLogRecord;
import undertone.com.logging.util.MonitorMetric;
import undertone.com.logging.util.MonitorService;

import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static undertone.com.logging.util.MonitorMetric.FLUENTD_SEND_DURATION;
import static undertone.com.logging.util.MonitorMetric.FLUENTD_SEND_FAILURE;

@Slf4j
@Service
public class FluentdSendService {

  @Value("${fluentd.url}")
  private String fluentdUrlBase;

  @Value("${fluentd.max.timeout}")
  private int fluentdMaxTimeout;

  @Autowired
  private MonitorService monitorService;

  @Autowired
  private ObjectMapper objectMapper;

  private final CsvMapper mapper = new CsvMapper();

  @LoggingServiceExceptionHandled
  @Async(AsyncConfig.ASYNC_FLUENTD)
  public CompletableFuture<HttpStatus> sendAsync(String extension, CsvSchema schema, MonitorMetric sentMonitorMetric, List<? extends FluentdLog> logs) {
    final List<FluentdLogRecord> messageLogs = getRequestBody(schema, logs);

    ResponseEntity<String> response = send(extension, messageLogs);

    if (response.getStatusCode() == HttpStatus.OK) {

      monitorService.incrementCounter(sentMonitorMetric, logs.size());
      log.info("Sent {} logs of type {},", logs.size(), extension);
    } else {
      monitorService.incrementCounter(FLUENTD_SEND_FAILURE, logs.size());
      throw new RuntimeException("Sending to FluentD failed with code " + response.getStatusCode());
    }

    return CompletableFuture.completedFuture(response.getStatusCode());
  }

  private List<FluentdLogRecord> getRequestBody(CsvSchema schema, List<? extends FluentdLog> logs) {
    return logs.stream()
      .map(fl -> toFluentDLogRecord(schema, fl))
      .collect(toList());
  }

  private FluentdLogRecord toFluentDLogRecord(CsvSchema schema, FluentdLog logData) {
    try {
      final String csv = mapper.writer(schema)
        .writeValueAsString(logData)
        .replace("\n", "");

      log.debug("{}: '{}'", logData.getClass().getSimpleName(), csv);

      final byte[] bytes = csv.getBytes();
      return new FluentdLogRecord(new String(Base64.getEncoder().encode(bytes)));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private ResponseEntity<String> send(final String extension, final List<FluentdLogRecord> messageLogs) {
    try {
      final RestTemplate restTemplate = new RestTemplateBuilder()
        .setConnectTimeout(Duration.ofMillis(fluentdMaxTimeout))
        .build();

      final String body = objectMapper.writeValueAsString(messageLogs);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add("json", body);
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

      Stopwatch stopwatch = Stopwatch.createStarted();
      ResponseEntity<String> response = restTemplate.postForEntity(fluentdUrlBase + extension, request, String.class);
      monitorService.gauge(FLUENTD_SEND_DURATION, stopwatch.elapsed(TimeUnit.MILLISECONDS));

      return response;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

