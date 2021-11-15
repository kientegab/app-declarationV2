package com.mfptps.appdgessddi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Required information to send as response in the {@code login} request
 */
/* @AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"jwtId"})
@NoArgsConstructor */
public class AuthenticationInformationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private long expiresIn;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "jti")
    private String jwtId;

    private Map<String, Object> additionalInfo;

    public AuthenticationInformationDto() {
    }

    public AuthenticationInformationDto(String accessToken, long expiresIn, String refreshToken,
            String jwtId, Map<String, Object> additionalInfo) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.jwtId = jwtId;
        this.additionalInfo = additionalInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jwtId == null) ? 0 : jwtId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthenticationInformationDto other = (AuthenticationInformationDto) obj;
        if (jwtId == null) {
            if (other.jwtId != null)
                return false;
        } else if (!jwtId.equals(other.jwtId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AuthenticationInformationDto [accessToken=" + accessToken + ", additionalInfo=" + additionalInfo
                + ", expiresIn=" + expiresIn + ", jwtId=" + jwtId + ", refreshToken=" + refreshToken + ", tokenType="
                + tokenType + "]";
    }
}
