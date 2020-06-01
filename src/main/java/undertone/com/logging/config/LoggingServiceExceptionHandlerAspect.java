package undertone.com.logging.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import undertone.com.logging.util.MonitorService;

import static undertone.com.logging.util.MonitorMetric.GLOBAL_ERROR;

@Aspect
@Component
@Slf4j
public class LoggingServiceExceptionHandlerAspect {

  @Autowired
  private MonitorService monitorService;

  @Pointcut("@annotation( undertone.com.logging.config.LoggingServiceExceptionHandled)")
  public void handledMethods() {
  }

  @AfterThrowing(pointcut = "handledMethods()", throwing = "ex")
  public void handleTheException(Exception ex) {

    log.error("Exception occurred: {}", ex.getMessage());
    monitorService.incrementCounter(GLOBAL_ERROR);
  }
}
