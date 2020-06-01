package undertone.com.logging.dto.fluentd;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
  "request_time",             // #1
  "conversion_time",          // #2
  "user_id",                  // #3
  "tracker_id",               // #4
  "tracker_status",           // #5
  "action_type",              // #6
  "ad_id",                    // #7
  "zone_id",                  // #8
  "action_time",              // #9
  "conversion_connection",    // #10
  "client_ip",                // #11
  "language",                 // #12
  "user_agent",               // #13
  "profile_ids",              // #14
  "contextual_category_id",   // #15
  "contextual_language",      // #16
  "referrer_domain",          // #17
  "wurfl_device_id",          // #18
  "variables",                // #19
  "latitude",                 // #20
  "longitude",                // #21
  "mobile_carrier",           // #22
  "country_code",             // #23
  "region",                   // #24
  "city",                     // #25
  "dma_code",                 // #26
  "zip_code",                 // #27
  "gmt_offset",               // #28
  "netspeed",                 // #29
  "isp",                      // #30
  "tracker_banner_ids_list",  // #31
  "advertiser_id",            // #32
  "partner_id",               // #33
  "profile_id",               // #34
  "reject_reason",            // #35
  "request_id",               // #36
  "log_line_id",              // #37
  "is_converted",             // #38
  "referer_url",              // #39
  "is_gdpr"                   // #40
})

@Getter
@Builder
public class ConversionFluentdLog extends FluentdLog {

  @JsonProperty("request_time")
  private final String requestTime;

  @JsonProperty("conversion_time")
  private final String conversionTime;

  @JsonProperty("user_id")
  private final String userId;

  @JsonProperty("tracker_id")
  private final int trackerId;

  @JsonProperty("tracker_status")
  private final int trackerStatus = DEFAULT_TRACKER_STATUS;

  @JsonProperty("action_type")
  private final int actionType = DEPRECATED_INT_VALUE;

  @JsonProperty("ad_id")
  private final int adId = DEPRECATED_INT_VALUE;

  @JsonProperty("zone_id")
  private final int zoneId = DEPRECATED_INT_VALUE;

  @JsonProperty("action_time")
  private final String action_time = DEPRECATED_LOG_VALUE;

  @JsonProperty("conversion_connection")
  private final int conversionConnection = DEPRECATED_INT_VALUE;

  @JsonProperty("client_ip")
  private final String clientIp;

  @JsonProperty("language")
  private final String language;

  @JsonProperty("user_agent")
  private final String userAgent;

  @JsonProperty("profile_ids")
  private final String profileIds = DEPRECATED_LOG_VALUE;

  @JsonProperty("contextual_category_id")
  private final String contextualCategoryId = DEPRECATED_LOG_VALUE;

  @JsonProperty("contextual_language")
  private final String contextualLanguage = DEPRECATED_LOG_VALUE;

  @JsonProperty("referrer_domain")
  private final String referrerDomain = DEPRECATED_LOG_VALUE;

  @JsonProperty("wurfl_device_id")
  private final String wurflDeviceId = DEPRECATED_LOG_VALUE;

  @JsonProperty("variables")
  private final String variables;

  @JsonProperty("latitude")
  private final String latitude;

  @JsonProperty("longitude")
  private final String longitude;

  @JsonProperty("mobile_carrier")
  private final String mobileCarrier;

  @JsonProperty("country_code")
  private final String countryCode;

  @JsonProperty("region")
  private final String region;

  @JsonProperty("city")
  private final String city;

  @JsonProperty("dma_code")
  private final String dmaCode;

  @JsonProperty("zip_code")
  private final String zipCode;

  @JsonProperty("gmt_offset")
  private final String gmtOffset;

  @JsonProperty("netspeed")
  private final String netspeed;

  @JsonProperty("isp")
  private final String isp;

  @JsonProperty("tracker_banner_ids_list")
  private final String tracker_banner_ids_list = DEPRECATED_LOG_VALUE;

  @JsonProperty("advertiser_id")
  private final String advertiser_id;

  @JsonProperty("partner_id")
  private final String partnerId;

  @JsonProperty("profile_id")
  private final String profileId;

  @JsonProperty("reject_reason")
  private final String reject_reason = DEPRECATED_LOG_VALUE;

  @JsonProperty("request_id")
  private final String requestId = DEPRECATED_LOG_VALUE;

  @JsonProperty("log_line_id")
  private final String logLineId;

  @JsonProperty("is_converted")
  private final int isConverted = DEPRECATED_INT_VALUE;

  @JsonProperty("referer_url")
  private final String refererUrl;

  @JsonProperty("is_pii_forbidden")
  private final int isPiiForbidden;

}
