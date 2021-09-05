package com.cateam.coalive.config.security.controller;

import com.cateam.coalive.config.security.component.KakaoServiceProvider;
import com.cateam.coalive.config.security.domain.KakaoOAuthRequest;
import com.cateam.coalive.config.security.domain.KakaoProperty;
import com.cateam.coalive.config.security.domain.KakaoResponse;
import com.cateam.coalive.config.security.token.TokenProvider;
import com.cateam.coalive.web.member.component.NickNameCreator;
import com.cateam.coalive.web.member.domain.Member;
import com.cateam.coalive.web.member.repository.MemberRepository;
import com.cateam.coalive.web.member.type.AuthServer;
import com.cateam.coalive.web.member.type.MemberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/oauth2/code")
@RequiredArgsConstructor
public class OAuth2Controller {
    private final KakaoProperty kakaoProperty;
    private final TokenProvider tokenProvider;
    private final KakaoServiceProvider kakaoServiceProvider;
    private final NickNameCreator nickNameCreator;
    private final MemberRepository memberRepository;


    @GetMapping("/kakao")
    public String loginKakao(@RequestParam String code) {
        KakaoResponse kakaoResponse = requestToken(code);
        String uid = kakaoServiceProvider.getKakaoUid(kakaoResponse.getAccessToken());
        saveOrUpdate(uid);

        return tokenProvider.createToken(kakaoResponse.getUid());
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

        KakaoResponse kakaoResponse = parseKakaoResponse(response.getBody());
        String uid = kakaoServiceProvider.getKakaoUid(kakaoResponse.getAccessToken());
        kakaoResponse.setUid(uid);

        return kakaoResponse;
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
            throw new IllegalStateException("카카오 로그인을 할 수 없습니다.");
        }
    }

    private Member saveOrUpdate(String uid) {
        Member member = memberRepository.findByUid(uid)
                .orElse(Member.builder()
                        .uid(uid)
                        .type(MemberType.USER)
                        .name(nickNameCreator.createNickName())
                        .authServer(AuthServer.KAKAO)
                        .build());

        return memberRepository.save(member);
    }
}
