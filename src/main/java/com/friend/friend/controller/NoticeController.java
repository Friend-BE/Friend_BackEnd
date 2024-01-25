package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Notice;
import com.friend.friend.dto.BoardResponseDto;
import com.friend.friend.dto.NoticeRequestDto;
import com.friend.friend.dto.NoticeResponseDto;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.service.NoticeService;
import java.util.List;

import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NoticeController {
    private final NoticeService noticeService;

    //전체목록조회
    @Operation(summary = "전체 공지사항 목록 조회")
    @GetMapping("/posts")
    public ResponseEntity getNotices() {
        try{
            List<NoticeResponseDto> notices = noticeService.getNotices();
            return new ResponseEntity<>(Response.success(notices), HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    //공지사항 작성
    @Operation(summary = "공지사항 작성")
    @PostMapping("/post")
    public ResponseEntity createNotice(@RequestBody NoticeRequestDto requestDto) {
        boolean result = noticeService.createNotice(requestDto);
//        return noticeService.createNotice(requestDto)]
        if (result) {
            return new ResponseEntity<>(Response.success(requestDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    //선택한 공지사항 조회
    @Operation(summary = "선택한 공지사항 조회")
    @GetMapping("/post/{id}")
    public ResponseEntity getNotice(@PathVariable Long id) {
        Optional<NoticeResponseDto> notice = noticeService.getPost(id);
        if (notice.isPresent()) {
            return new ResponseEntity<>(Response.success(notice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    //공지사항 수정
    @Operation(summary = "공지사항 수정")
    @PutMapping("/post/{id}")
    public ResponseEntity updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto)
            throws Exception {
        try {
            NoticeResponseDto noticeResponseDto = noticeService.updateNotice(id, requestDto);
            return new ResponseEntity<>(Response.success(noticeResponseDto), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    //공지사항 삭제
    @Operation(summary = "공지사항 삭제")
    @DeleteMapping("/post/{id}")
    public ResponseEntity deleteNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto)
            throws Exception {
        try {
            SuccessResponseDto successResponseDto = noticeService.deletePost(id, requestDto);
            return new ResponseEntity<>(Response.success(successResponseDto), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }

    }
}
