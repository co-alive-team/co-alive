package com.cateam.coalive.config.security.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final TokenProperty tokenProperty;


    public String createToken(String uid) {
        Date nowTime = new Date();

        return Jwts.builder()
                .setSubject(uid)
                .setIssuedAt(nowTime)
                .setExpiration(new Date(nowTime.getTime() + tokenProperty.getExpiredTime()))
                .signWith(SignatureAlgorithm.HS256, tokenProperty.getSecretKey())
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(tokenProperty.getSecretKey())
                    .parse(token);
        } catch (Exception e) {
            throw new IllegalStateException("token이 유효하지 않습니다.");
        }
    }

    public String getUidFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(tokenProperty.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
