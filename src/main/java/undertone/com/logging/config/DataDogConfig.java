package undertone.com.logging.config;

import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataDogConfig {

  @Value("${dogstatsd.host.ip}")
  private String dogStatsdHostIp;

  @Value("${dogstatsd.host.port}")
  private int dogStatsdHostPort;

  @Bean
  public StatsDClient statsDClient() {

    return new NonBlockingStatsDClientBuilder()
      .prefix("logging.service")
      .hostname(dogStatsdHostIp)
      .port(dogStatsdHostPort)
      .build();
  }
}
