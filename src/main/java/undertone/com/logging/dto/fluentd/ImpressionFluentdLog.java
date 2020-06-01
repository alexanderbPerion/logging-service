package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
  "request_time",         // #1
  "request_id",           // #2
  "impr_time",            // #3
  "utid",                 // #4
  "price_tag",            // #5
  "experiment_id",        // #6  ,deprecated
  "uas_version",          // #7  ,deprecated
  "slice_id",             // #8  ,deprecated
  "traffic_data",         // #9  ,deprecated
  "is_hb",                // #10 ,deprecated
  "drt",                  // #11
  "is_gdpr",              // #12 ,deprecated
  "aws_site",             // #13 ,deprecated
  "uri",                  // #14
  "external_user_id",     // #15
  "zone_id",              // #16
  "banner_id",            // #17

})
@Getter
@Builder
public class ImpressionFluentdLog extends FluentdLog {

  @JsonProperty("request_time")
  private final String requestTime;

  @JsonProperty("request_id")
  private final String requestId;

  @JsonProperty("impr_time")
  private final String imprTime;

  @JsonProperty("utid")
  private final String utid;

  @JsonProperty("price_tag")
  private final String priceTag;

  @JsonProperty("experiment_id")
  private final String experimentId = DEPRECATED_LOG_VALUE;

  @JsonProperty("uas_version")
  private final String uasVersion = DEPRECATED_LOG_VALUE;

  @JsonProperty("slice_id")
  private final String sliceId = DEPRECATED_LOG_VALUE;

  @JsonProperty("traffic_data")
  private String trafficData = DEPRECATED_LOG_VALUE;

  @JsonProperty("is_hb")
  private final String isHb = DEPRECATED_LOG_VALUE;

  @JsonProperty("drt")
  private final String drt = "0";

  @JsonProperty("is_gdpr")
  private final String isGdpr = DEPRECATED_LOG_VALUE;

  @JsonProperty("aws_site")
  private final String awsSite = DEPRECATED_LOG_VALUE;

  @JsonProperty("uri")
  private final String uri;

  @JsonProperty("external_user_id")
  private final String externalUserId;

  @JsonProperty("zone_id")
  private final String zoneId;

  @JsonProperty("banner_id")
  private final String bannerId;

}
