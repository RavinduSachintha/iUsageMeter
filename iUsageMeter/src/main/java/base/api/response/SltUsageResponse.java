package base.api.response;

import base.api.response.models.SltVasDataPackageSummary;
import base.api.response.models.SltVasDataUsageDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class SltUsageResponse extends GenericResponse {
    @JsonProperty("package_name")
    private String packageName;

    @JsonProperty("package_summary")
    private SltVasDataPackageSummary packageSummary;

    @JsonProperty("usageDetails")
    private SltVasDataUsageDetails[] usageDetails;

    @JsonProperty("reported_time")
    private String reportedTime;

    public String getPackageName() {
        return packageName;
    }

    public SltVasDataPackageSummary getPackageSummary() {
        return packageSummary;
    }

    public SltVasDataUsageDetails[] getUsageDetails() {
        return usageDetails;
    }

    public String getReportedTime() {
        return reportedTime;
    }
}
