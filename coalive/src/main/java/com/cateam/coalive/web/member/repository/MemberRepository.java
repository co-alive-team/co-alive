package com.cateam.coalive.web.member.repository;

import com.cateam.coalive.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUid(String uid);

    Optional<Member> findByName(String name);
}
