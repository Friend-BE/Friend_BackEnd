package com.friend.friend.repository;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.AccountStatusEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmail(String email);

    List<Member> findByStatus(AccountStatusEnum status);
}
