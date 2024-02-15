package com.friend.friend.repository;

import com.friend.friend.domain.board.Qa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QaRepository extends JpaRepository<Qa,Long> {

    List<Qa> findByAuthor(String Author);
}
