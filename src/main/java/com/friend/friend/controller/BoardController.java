package com.friend.friend.controller;

import com.friend.friend.domain.board.Review;
import com.friend.friend.dto.BoardRequestsDto;
import com.friend.friend.dto.BoardResponseDto;
import com.friend.friend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/review")
    public BoardResponseDto saveReview(@RequestBody BoardRequestsDto request){
        Review review = new Review();
        review.setTitle(request.getTitle());
        review.setBody(request.getBody());
        review.setAuthor(request.getAuthor());
        review.setViews(0L);

        Long board_id = boardService.saveBoard(review);
        return BoardResponseDto.convertBoardResponseDTO(boardService.findOne(board_id));
    }
}
