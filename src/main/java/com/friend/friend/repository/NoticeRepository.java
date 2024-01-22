package com.friend.friend.repository;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    List<Notice> findAllByOrderByUpdatedAtDesc();//공지사항의 업데이트 시간을 내림차순으로 정렬 -> 최신글이 맨위로 조회
}
