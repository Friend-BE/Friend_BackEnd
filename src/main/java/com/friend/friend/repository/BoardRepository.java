package com.friend.friend.repository;

import com.friend.friend.domain.board.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;
    public void save(Board board){
        if(board.getId()==null){
            em.persist(board);
        }else{
            em.merge(board);
        }
    }
    public Board findOne(Long id){
        return em.find(Board.class,id);
    }
}