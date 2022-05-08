package base.api.response.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SltVasDataUsageDetails {
    @JsonProperty("name")
    String name;

    @JsonProperty("limit")
    double limit;

    @JsonProperty("used")
    double used;

    @JsonProperty("remaining")
    double remaining;

    @JsonProperty("percentage")
    double percentage;

    @JsonProperty("volume_unit")
    String volumeUnit;

    @JsonProperty("expiry_date")
    String expiryDate;

    @JsonProperty("unsubscribable")
    boolean unsubscribable;

    @JsonProperty("timestamp")
    long timestamp;

    @JsonProperty("subscriptionid")
    String subscriptionId;

    public String getName() {
        return name;
    }

    public double getLimit() {
        return limit;
    }

    public double getUsed() {
        return used;
    }

    public double getRemaining() {
        return remaining;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean isUnsubscribable() {
        return unsubscribable;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
