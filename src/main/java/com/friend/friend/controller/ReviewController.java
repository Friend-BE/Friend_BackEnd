package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.dto.*;
import com.friend.friend.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getReviews(){
        List<ReviewResponseDto> reviews = reviewService.getReviews();
        if(!reviews.isEmpty()){
            return new ResponseEntity<>(Response.success(reviews), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 후기 작성
     */
    @Operation(summary = "후기 작성")
    @PostMapping("/review")
    public ResponseEntity createReview(@RequestBody ReviewRequestsDto request){
        ReviewResponseDto review = reviewService.createReview(request);
        if (review!=null) {
            return new ResponseEntity<>(Response.success(review), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 후기 상세 조회
     */
    @Operation(summary = "후기 상세 조회")
    @GetMapping("/review/{id}")
//    public ReviewResponseDto getReview(@RequestParam Long id){
//        return reviewService.getReview(id);
//    }
    public ResponseEntity getReview(@RequestParam Long id){
        ReviewResponseDto review = reviewService.getReview(id);
        if (review!=null) {
            return new ResponseEntity<>(Response.success(review), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 후기 수정
     */
    @Operation(summary = "후기 수정")
    @PutMapping("/review/{id}")
//    public ReviewResponseDto updateReview(@RequestBody ReviewRequestsDto request,@PathVariable Long id){
//        return reviewService.updateReview(id,request);
//    }
    public ResponseEntity updateReview(@RequestBody ReviewRequestsDto request, @PathVariable Long id){
        ReviewResponseDto review = reviewService.updateReview(id, request);
        if (review!=null) {
            return new ResponseEntity<>(Response.success(review), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "후기 삭제")
    @DeleteMapping("/review/{id}")
//    public SuccessResponseDto deleteNotice(@PathVariable Long id, @RequestBody ReviewRequestsDto request)
//            throws Exception {
//        return reviewService.deleteReview(id, request);
//    }
    public ResponseEntity deleteNotice(@PathVariable Long id, @RequestBody ReviewRequestsDto request)
            throws Exception {
        try{
            SuccessResponseDto successResponseDto = reviewService.deleteReview(id, request);
            return new ResponseEntity<>(Response.success(successResponseDto),HttpStatus.OK);
        }catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }
}
