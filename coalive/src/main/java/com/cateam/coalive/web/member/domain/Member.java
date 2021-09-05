package com.cateam.coalive.web.member.domain;

import com.cateam.coalive.web.common.domain.BaseEntity;
import com.cateam.coalive.web.member.type.AuthServer;
import com.cateam.coalive.web.member.type.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String uid;

    @Column(unique = true, nullable = false, length = 15)
    private String name;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Enumerated(EnumType.STRING)
    private AuthServer authServer;


    public Member updateAvatar(String avatar) {
        this.avatar = avatar;

        return this;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
