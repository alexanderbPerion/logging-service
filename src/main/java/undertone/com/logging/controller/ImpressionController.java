package undertone.com.logging.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Controller;
import undertone.com.logging.config.LoggingServiceExceptionHandled;
import undertone.com.logging.dto.SqsMessage;
import undertone.com.logging.service.fluentd.ImpressionLogService;
import undertone.com.logging.util.MonitorService;

import java.util.concurrent.ExecutionException;

import static undertone.com.logging.util.MonitorMetric.RECEIVED_IMPRESSIONS;

@Slf4j
@Controller
@EnableSqs
public class ImpressionController {

  @Autowired
  ImpressionLogService impressionLogService;

  @Autowired
  private MonitorService monitorService;

  @LoggingServiceExceptionHandled
  @SqsListener(value = "${sqs.impression}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
  public void receiveImpression(SqsMessage message, Acknowledgment acknowledgment) throws ExecutionException, InterruptedException {

    monitorService.incrementCounter(RECEIVED_IMPRESSIONS);

    log.debug("Received impression message: {}", message);

    impressionLogService.logMessage(message, acknowledgment);
  }

}
