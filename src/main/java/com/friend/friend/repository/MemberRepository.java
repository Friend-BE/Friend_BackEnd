package com.friend.friend.repository;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.AccountStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.friend.friend.domain.enums.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByEmail(String email);

    List<Member> findByStatus(AccountStatusEnum status);

    List<Member> findByStatusAndGenderAndCreatedAtBetween(AccountStatusEnum status, GenderEnum gender, LocalDateTime stDate, LocalDateTime enDate);

    void deleteByEmail(String email);


}
