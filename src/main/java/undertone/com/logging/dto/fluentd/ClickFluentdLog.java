package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
  "request_time",             // #1
  "request_id",               // #2
  "click_time",               // #3
  "user_id",                  // #4
  "zone_id",                  // #5
  "ad_id",                    // #6
  "client_ip",                // #7
  "language",                 // #8
  "user_agent",               // #9
  "referrer_domain",          // #10
  "matching_profile_ids",     // #11
  "optimizer_ids",            // #12
  "profile_ids",              // #13
  "contextual_category_id",   // #14
  "contextual_language",      // #15
  "wurfl_device_id",          // #16
  "latitude",                 // #17
  "longitude",                // #18
  "mobile_carrier",           // #19
  "country_code",             // #20
  "region",                   // #21
  "city",                     // #22
  "dma_code",                 // #23
  "zip_code",                 // #24
  "gmt_offset",               // #25
  "net_speed",                // #26
  "isp",                      // #27
  "experiment_id",            // #28
  "uas_version",              // #29
  "is_gdpr",                  // #30
  "temp_data"
})

@Getter
@Builder
public class ClickFluentdLog extends FluentdLog {

  @JsonProperty("request_time")
  private final String requestTime;

  @JsonProperty("request_id")
  private final String requestId;

  @JsonProperty("click_time")
  private final String clickTime;

  @JsonProperty("user_id")
  private final String userId;

  @JsonProperty("zone_id")
  private final String zoneId;

  @JsonProperty("ad_id")
  private final String adId;

  @JsonProperty("client_ip")
  private final String clientIp = DEPRECATED_LOG_VALUE;

  @JsonProperty("language")
  private final String language = DEPRECATED_LOG_VALUE;

  @JsonProperty("user_agent")
  private final String userAgent;

  @JsonProperty("referrer_domain")
  private final String referrerDomain = DEPRECATED_LOG_VALUE;

  @JsonProperty("matching_profile_ids")
  private final String matchingProfileIds = DEPRECATED_LOG_VALUE;

  @JsonProperty("optimizer_ids")
  private final String optimizerIds = DEPRECATED_LOG_VALUE;

  @JsonProperty("profile_ids")
  private final String profileIds = DEPRECATED_LOG_VALUE;

  @JsonProperty("contextual_category_id")
  private final String contextualCategoryId = DEPRECATED_LOG_VALUE;

  @JsonProperty("contextual_language")
  private final String contextualLanguage = DEPRECATED_LOG_VALUE;

  @JsonProperty("wurfl_device_id")
  private final String wurflDeviceId = DEPRECATED_LOG_VALUE;

  @JsonProperty("latitude")
  private final String latitude = DEPRECATED_LOG_VALUE;

  @JsonProperty("longitude")
  private final String longitude = DEPRECATED_LOG_VALUE;

  @JsonProperty("mobile_carrier")
  private final String mobileCarrier = DEPRECATED_LOG_VALUE;

  @JsonProperty("country_code")
  private final String countryCode = DEPRECATED_LOG_VALUE;

  @JsonProperty("region")
  private final String region = DEPRECATED_LOG_VALUE;

  @JsonProperty("city")
  private final String city = DEPRECATED_LOG_VALUE;

  @JsonProperty("dma_code")
  private final String dmaCode = DEPRECATED_LOG_VALUE;

  @JsonProperty("zip_code")
  private final String zipCode = DEPRECATED_LOG_VALUE;

  @JsonProperty("gmt_offset")
  private final String gmtOffset = DEPRECATED_LOG_VALUE;

  @JsonProperty("net_speed")
  private final String net_speed = DEPRECATED_LOG_VALUE;

  @JsonProperty("isp")
  private final String isp = DEPRECATED_LOG_VALUE;

  @JsonProperty("experiment_id")
  private final String experimentId = DEPRECATED_LOG_VALUE;

  @JsonProperty("uas_version")
  private final String uasVersion = DEPRECATED_LOG_VALUE;

  @JsonProperty("is_gdpr")
  private final String isGdpr = DEPRECATED_LOG_VALUE;

}
