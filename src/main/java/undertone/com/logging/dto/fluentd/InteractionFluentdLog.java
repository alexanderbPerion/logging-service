package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
  "request_time",             // #1
  "request_id",               // #2
  "event_code",               // #3
  "zone_id",                  // #4
  "ad_id",                    // #5
  "client_ip",                // #6
  "event_time",               // #7
  "user_id",                  // #8
  "profile_ids",              // #9
  "contextual_category_ids",  // #10
  "contextual_language",      // #11
  "element_code",             // #12
  "time_spent",               // #13
  "time_viewed",              // #14
  "user_agent",               // #15
  "referrer_url",             // #16
  "wurfl_device_id",          // #17
  "latitude",                 // #18
  "longitude",                // #19
  "mobile_carrier",           // #20
  "country_code",             // #21
  "region",                   // #22
  "city",                     // #23
  "dma_code",                 // #24
  "zip_code",                 // #25
  "gmt_offset",               // #26
  "net_speed",                // #27
  "isp",                      // #28
  "experiment_id",            // #29
  "uas_version",              // #30
  "is_gdpr"                   // #31

})
@Getter
@Builder
public class InteractionFluentdLog extends FluentdLog {

  @JsonProperty("request_time")
  private final String requestTime;

  @JsonProperty("request_id")
  private final String requestId;

  @JsonProperty("event_code")
  private final String eventCode;

  @JsonProperty("zone_id")
  private final String zoneId;

  @JsonProperty("ad_id")
  private final String adId;

  @JsonProperty("client_ip")
  private final String clientIp = DEPRECATED_LOG_VALUE;

  @JsonProperty("event_time")
  private String eventTime;

  @JsonProperty("user_id")
  private final String userId = DEPRECATED_LOG_VALUE;

  @JsonProperty("profile_ids")
  private final String profileIds = DEPRECATED_LOG_VALUE;

  @JsonProperty("contextual_category_ids")
  private final String contextualCategoryIds = DEPRECATED_LOG_VALUE;

  @JsonProperty("contextual_language")
  private final String contextualLanguage = DEPRECATED_LOG_VALUE;

  @JsonProperty("element_code")
  private final String elementCode;

  @JsonProperty("time_spent")
  private final int timeSpent;

  @JsonProperty("time_viewed")
  private int timeViewed;

  @JsonProperty("user_agent")
  private final String userAgent;

  @JsonProperty("referrer_url")
  private final String referrerUrl = DEPRECATED_LOG_VALUE;

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
  private final String netSpeed = DEPRECATED_LOG_VALUE;

  @JsonProperty("isp")
  private final String isp = DEPRECATED_LOG_VALUE;

  @JsonProperty("experiment_id")
  private final String experimentId = DEPRECATED_LOG_VALUE;

  @JsonProperty("uas_version")
  private final String uasVersion = DEPRECATED_LOG_VALUE;

  @JsonProperty("is_gdpr")
  private final String isGdpr = DEPRECATED_LOG_VALUE;

}
