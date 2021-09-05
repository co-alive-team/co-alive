package com.cateam.coalive.config.security.component;

import com.cateam.coalive.config.security.domain.KakaoProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoServiceProvider {
    private final KakaoProperty kakaoProperty;
    private final ObjectMapper objectMapper;


    public String getKakaoUid(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                kakaoProperty.getUserInfoUri(),
                HttpMethod.GET,
                kakaoRequest,
                String.class
        );

        try {
            Map<String, Object> stringMap = objectMapper.readValue(response.getBody(), Map.class);

            return String.valueOf(stringMap.get("id"));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("kakao id를 가져올 수 없습니다.");
        }
    }
}
