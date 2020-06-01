package undertone.com.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import undertone.com.logging.util.MonitorService;

import static undertone.com.logging.util.MonitorMetric.GLOBAL_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @Autowired
  private MonitorService monitorService;

  @ExceptionHandler(Exception.class)
  public void error(Exception ex) {

    log.error("Exception occurred: {}", ex.getMessage());
    monitorService.incrementCounter(GLOBAL_ERROR);
  }
}
