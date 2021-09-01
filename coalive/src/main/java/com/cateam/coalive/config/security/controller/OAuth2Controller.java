package com.cateam.coalive.config.security.controller;

import com.cateam.coalive.config.security.domain.KakaoOAuthRequest;
import com.cateam.coalive.config.security.domain.KakaoProperty;
import com.cateam.coalive.config.security.domain.KakaoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/oauth2/code")
@RequiredArgsConstructor
public class OAuth2Controller {
    private final KakaoProperty kakaoProperty;


    @GetMapping("/kakao")
    public KakaoResponse loginKakao(@RequestParam String code) {
        KakaoResponse kakaoResponse = requestToken(code);

        return kakaoResponse;
    }

    private KakaoResponse requestToken(String code) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = KakaoOAuthRequest.headers;
        MultiValueMap<String, String> params = initMultiValueMap(code);
        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = template.exchange(
                kakaoProperty.getTokenUri(),
                HttpMethod.POST,
                kakaoRequest,
                String.class
        );

        return parseKakaoResponse(response.getBody());
    }

    private MultiValueMap initMultiValueMap(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoProperty.getAuthorizationGrantType());
        params.add("client_id", kakaoProperty.getClientId());
        params.add("redirect_uri", kakaoProperty.getRedirectUri());
        params.add("client_secret", kakaoProperty.getClientSecret());
        params.add("code", code);

        return params;
    }

    private KakaoResponse parseKakaoResponse(String body) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(body, KakaoResponse.class);
        } catch (JsonProcessingException e) {
            log.error("kakao 로그인 실패");
            return null;
        }
    }
}
