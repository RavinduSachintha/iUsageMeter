package base.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class SltAuthResponse extends GenericResponse {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("metadata")
    private String metadata;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("consented_on")
    private long consentedOn;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token_expires_in")
    private long refreshTokenExpiresIn;

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getMetadata() {
        return metadata;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public long getConsentedOn() {
        return consentedOn;
    }

    public String getScope() {
        return scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }
}
