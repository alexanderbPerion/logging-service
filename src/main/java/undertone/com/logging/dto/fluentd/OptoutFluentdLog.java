package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
  "request_time",       // #1
  "user_id_encoded",    // #2
  "oo_operation"        // #3
})

@Getter
@Builder
public class OptoutFluentdLog extends FluentdLog {

  @JsonProperty("request_time")
  private final String requestTime;

  @JsonProperty("user_id_encoded")
  private final String userIdEncoded;

  @JsonProperty("oo_operation")
  private final String ooOperation;

}
