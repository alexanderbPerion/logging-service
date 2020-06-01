package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
  "request_time",         // #1
  "profile_time",         // #2
  "user_id",              // #3
  "all_profiles",         // #4
  "new_profiles",         // #5
  "ip",                   // #6
  "opt_out",              // #7
  "country_code",         // #8
  "partner_id",           // #9
  "is_pii_forbidden"      // #10
})

@Getter
@Builder
public class ProfileFluentdLog extends FluentdLog {

  @JsonProperty("request_time")
  private final String requestTime;

  @JsonProperty("profile_time")
  private final String profileTime;

  @JsonProperty("user_id")
  private final String userId;

  @JsonProperty("all_profiles")
  private final String allProfiles;

  @JsonProperty("new_profiles")
  private final String newProfiles;

  @JsonProperty("ip")
  private final String ip;

  @JsonProperty("opt_out")
  private final int optOut;

  @JsonProperty("country_code")
  private final String countryCode;

  @JsonProperty("partner_id")
  private final String partnerId;

  @JsonProperty("is_pii_forbidden")
  private final int isPiiForbidden;

}
