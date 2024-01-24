package com.friend.friend.repository;

import com.friend.friend.domain.board.Qa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QaRepository extends JpaRepository<Qa,Long> {

}
