package undertone.com.logging.util;

import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorService {

  @Autowired
  protected StatsDClient statsDClient;

  public void incrementCounter(MonitorMetric monitorMetric) {
    statsDClient.incrementCounter(monitorMetric.toString());
  }

  public void incrementCounter(MonitorMetric monitorMetric, int amount) {
    statsDClient.incrementCounter(monitorMetric.toString(), amount);
  }

  public void gauge(MonitorMetric monitorMetric, long value) {
    statsDClient.gauge(monitorMetric.toString(), value);
  }

}
