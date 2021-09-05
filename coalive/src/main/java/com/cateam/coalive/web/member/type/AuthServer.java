package com.cateam.coalive.web.member.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthServer {
    KAKAO("kakao"),
    GOOGLE("google"),
    NAVER("naver");

    private final String name;

    public static boolean isKakao(String name) {
        return KAKAO.name.equals(name);
    }
}
