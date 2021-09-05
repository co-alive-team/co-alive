package com.cateam.coalive.config.security.token;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TokenProperty {

    @Value("${auth.access.secret-key}")
    private String secretKey;

    @Value("${auth.access.expire-time}")
    private Long expiredTime;
}
