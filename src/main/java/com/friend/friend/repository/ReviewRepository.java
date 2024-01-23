package com.friend.friend.repository;

import com.friend.friend.domain.board.Notice;
import com.friend.friend.domain.board.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByOrderByCreatedAtDesc();//후기 생성 시간을 내림차순으로 정렬 -> 최신글이 맨위로 조회
}
