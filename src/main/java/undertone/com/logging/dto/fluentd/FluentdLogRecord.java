package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class FluentdLogRecord {

  @JsonProperty("message")
  private String logDataEncoded;
}
