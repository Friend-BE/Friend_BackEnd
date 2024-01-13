package com.friend.friend.repository;

import com.friend.friend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MemberRepository extends JpaRepository<Member, BigInteger> {
    Member findByEmail(String email);
    Member findByPhone(String number);
}

