package com.cateam.coalive.config.security.domain;

import org.springframework.http.HttpHeaders;

public class KakaoOAuthRequest {
    public static final HttpHeaders headers;

    static {
        headers = initHeader();
    }

    private static HttpHeaders initHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
