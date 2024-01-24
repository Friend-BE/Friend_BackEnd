package com.friend.friend.controller;

import com.friend.friend.dto.*;
import com.friend.friend.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 전체 후기 조회
     */
    @Operation(summary = "모든 후기 조회")
    @GetMapping("/reviews")
    public List<ReviewResponseDto> getReviews(){
        return reviewService.getReviews();
    }

    /**
     * 후기 작성
     */
    @Operation(summary = "후기 작성")
    @PostMapping("/review")
    public ReviewResponseDto createReview(@RequestBody ReviewRequestsDto request){
        return reviewService.createReview(request);
    }

    /**
     * 후기 상세 조회
     */
    @Operation(summary = "후기 상세 조회")
    @GetMapping("/review/{id}")
    public ReviewResponseDto getReview(@RequestParam Long id){
        return reviewService.getReview(id);
    }
    /**
     * 후기 수정
     */
    @Operation(summary = "후기 수정")
    @PutMapping("/review/{id}")
    public ReviewResponseDto updateReview(@RequestBody ReviewRequestsDto request,@PathVariable Long id){
        return reviewService.updateReview(id,request);
    }
    @Operation(summary = "후기 삭제")
    @DeleteMapping("/review/{id}")
    public SuccessResponseDto deleteNotice(@PathVariable Long id, @RequestBody ReviewRequestsDto request)
            throws Exception {
        return reviewService.deleteReview(id, request);
    }
}
