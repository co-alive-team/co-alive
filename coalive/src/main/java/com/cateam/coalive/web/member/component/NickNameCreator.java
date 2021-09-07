package com.cateam.coalive.web.member.component;

import com.cateam.coalive.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NickNameCreator {
    private static final int MAX_NICK_NAME_LENGTH = 8;
    public static final char[] CHAR_ARRAY = new char[62];

    private final MemberRepository memberRepository;


    static {
        int idx = 0;

        for (int i = 'a'; i <= 'z'; i++, idx++) {
            CHAR_ARRAY[idx] = (char) i;
        }
        for (int i = 'A'; i <= 'Z'; i++, idx++) {
            CHAR_ARRAY[idx] = (char) i;
        }
        for (int i = '0'; i <= '9'; i++, idx++) {
            CHAR_ARRAY[idx] = (char) i;
        }
    }

    public String createNickName() {
        String name;

        do {
            name = makeName();
        } while (NotExistName(makeName()));

        return name;
    }

    private String makeName() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < MAX_NICK_NAME_LENGTH; i++) {
            int idx = (int) (Math.random() * 62);
            sb.append(CHAR_ARRAY[idx]);
        }

        return sb.toString();
    }

    private boolean NotExistName(String name) {
        return memberRepository.findByName(name)
                .isPresent();
    }
}
