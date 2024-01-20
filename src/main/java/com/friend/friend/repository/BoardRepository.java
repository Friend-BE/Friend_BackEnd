package com.friend.friend.repository;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 단순 후기 상세 조회
     * 조회수 증가 X
     */
    public Board getReview(Long id){
        return em.find(Board.class,id);
    }

    /**
     * 게시판 조회수 1 증가
     */
    public void incrementReviewViews(Long id) {
        Board board = em.find(Board.class, id);
        if (board != null && board instanceof Review) {
            Review review = (Review) board;
            review.setViews(review.getViews() + 1);

            em.merge(review);
        }
    }


    /**
     * 모든 후기 리스트 조회
     */
    public List<Board> getAllReviews() {
        return em.createQuery("select b from Board b where type(b)=Review ",Board.class)
                .getResultList();
    }
}