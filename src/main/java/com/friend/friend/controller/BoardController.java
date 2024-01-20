package com.friend.friend.controller;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Review;
import com.friend.friend.dto.BoardRequestsDto;
import com.friend.friend.dto.BoardResponseDto;
import com.friend.friend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /**
     * 전체 후기 조회
     */
    @GetMapping("/reviews")
    public List<BoardResponseDto> getReviews(){
        List<Board> allReviews = boardService.getAllReviews();
        List<BoardResponseDto> result = new ArrayList<>();
        for (Board allReview : allReviews) {
            result.add(BoardResponseDto.convertBoardResponseDTO((Review) allReview));
        }
        return result;
    }

    /**
     * 후기 작성
     */
    @PostMapping("/review")
    public BoardResponseDto writeReview(@RequestBody BoardRequestsDto request){
        Review review = new Review();
        review.setTitle(request.getTitle());
        review.setBody(request.getBody());
        review.setAuthor(request.getAuthor());
        review.setViews(0L);

        Long board_id = boardService.saveBoard(review);
        return BoardResponseDto.convertBoardResponseDTO(boardService.getReview(board_id));
    }

    /**
     * 후기 상세 조회
     */
    @GetMapping("/review/{id}")
    public BoardResponseDto getReview(@RequestParam Long id){
        return BoardResponseDto.convertBoardResponseDTO(boardService.getReviewWithIncreaseView(id));
    }

    /**
     * 후기 수정
     */
    @PutMapping("/review/{id}")
    public BoardResponseDto updateReview(@RequestBody BoardRequestsDto request,@PathVariable Long id){
        Board review = boardService.getReview(id);
        review.setTitle(request.getTitle());
        review.setBody(request.getBody());
        Long board_id = boardService.saveBoard(review);

        return BoardResponseDto.convertBoardResponseDTO(boardService.getReview(board_id));
    }
}
