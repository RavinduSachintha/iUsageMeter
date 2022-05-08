package base.api.response.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SltVasDataPackageSummary {
    @JsonProperty("limit")
    double limit;

    @JsonProperty("used")
    double used;

    @JsonProperty("volume_unit")
    String volumeUnit;

    public double getLimit() {
        return limit;
    }

    public double getUsed() {
        return used;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }
}
