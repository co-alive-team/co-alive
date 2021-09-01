package com.cateam.coalive.config.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;

    private long expires_in;
    private long refresh_token_expires_in;


    public void setExpires_in(String expires_in) {
        this.expires_in = Long.parseLong(expires_in);
    }

    public void setRefresh_token_expires_in(String refresh_token_expires_in) {
        this.refresh_token_expires_in = Long.parseLong(refresh_token_expires_in);
    }

    public String getAccessToken() {
        return this.access_token;
    }

    public String getTokenType() {
        return this.token_type;
    }

    public String getRefreshToken() {
        return this.refresh_token;
    }

    public long getExpiresIn() {
        return this.expires_in;
    }

    public long getRefreshTokenExpiresIn() {
        return this.refresh_token_expires_in;
    }
}
