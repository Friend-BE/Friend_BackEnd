package com.friend.friend.service;

import com.friend.friend.domain.board.Review;
import com.friend.friend.dto.*;
import com.friend.friend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private  final ReviewRepository reviewRepository;

    /**
     * 모든 후기 조회(생성시간 기준으로 내림차순 정렬)
     */

    public List<ReviewResponseDto> getReviews() {
        return reviewRepository.findAllByOrderByCreatedAtDesc().stream().map(ReviewResponseDto::new).toList();
    }

    /**
     * 후기 작성
     */
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestsDto request) {
        Review review = new Review(request);
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    /**
     * 선택한 후기 조회
     */
    @Transactional
    public ReviewResponseDto getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디 입니다")
        );
        review.setViews(review.getViews()+1);
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    /**
     * 후기 수정
     */
    @Transactional
    public ReviewResponseDto updateReview(Long id,ReviewRequestsDto reviewRequestsDto){
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다")
        );
        review.update(reviewRequestsDto);
        return new ReviewResponseDto(review);
    }

    @Transactional
    public SuccessResponseDto deleteReview(Long id, ReviewRequestsDto request) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다")
        );

        reviewRepository.deleteById(id);
        return new SuccessResponseDto(true);
    }
}
