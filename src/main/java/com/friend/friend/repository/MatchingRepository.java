package com.friend.friend.repository;


import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Notice;
import com.friend.friend.domain.enums.AccountStatusEnum;
import com.friend.friend.domain.enums.GenderEnum;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchingRepository extends JpaRepository<Matching,Long> {

    List<Matching> findByMember_Id(Long id);

    Optional<List<Matching>> findByMember_IdAndStatus(Long memberId, MatchingStatusEnum status);

    Optional<List<Matching>> findByStatus(MatchingStatusEnum status);
    List<Matching> findAllByMember_IdOrderByCreatedAtDesc(Long memberId);
    List<Matching> findAllByStatusAndDateBetween(MatchingStatusEnum status, LocalDateTime start, LocalDateTime end);
}
