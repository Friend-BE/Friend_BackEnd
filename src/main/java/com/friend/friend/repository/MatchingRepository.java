package com.friend.friend.repository;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.friend.friend.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchingRepository extends JpaRepository<Matching,Long> {

    List<Matching> findByMember_Id(Long id);
}
