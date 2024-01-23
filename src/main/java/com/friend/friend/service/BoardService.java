package com.friend.friend.service;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Review;
import com.friend.friend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
//    private final BoardRepository boardRepository;
//
//    @Transactional
//    public Long saveBoard(Board board){
//        boardRepository.save(board);
//        return board.getId();
//    }
//    public Review getReview(Long boardId){
//        return (Review) boardRepository.getReview(boardId);
//    }
//    @Transactional
//    public Review getReviewWithIncreaseView(Long boardId) {
//        Review review = (Review) boardRepository.getReview(boardId);
//
//        if (review != null) {
//            boardRepository.incrementReviewViews(boardId);
//        }
//
//        return review;
//    }
//
//    public List<Board> getAllReviews() {
//        return boardRepository.getAllReviews();
//    }
}
