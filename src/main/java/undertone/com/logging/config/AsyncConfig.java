package undertone.com.logging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
  public static final String ASYNC_FLUENTD = "async-fluentd";

  @Value("${async.fluentd.core.pool.size}")
  private int asyncFluentdCorePoolSize;
  @Value("${async.fluentd.max.pool.size}")
  private int asyncFluentdMaxPoolSize;

  @Bean(ASYNC_FLUENTD)
  public Executor taskExecutorFluentd() {
    ThreadPoolTaskExecutor executor = getThreadPoolTaskExecutor(
      asyncFluentdCorePoolSize,
      asyncFluentdMaxPoolSize
    );
    executor.initialize();
    return executor;
  }

  private ThreadPoolTaskExecutor getThreadPoolTaskExecutor(int corePoolSize, int maxPoolSize) {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setThreadNamePrefix("async-flu-");
    return executor;
  }
}