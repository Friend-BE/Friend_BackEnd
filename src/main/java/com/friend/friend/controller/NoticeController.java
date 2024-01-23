package com.friend.friend.controller;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Notice;
import com.friend.friend.dto.BoardResponseDto;
import com.friend.friend.dto.NoticeRequestDto;
import com.friend.friend.dto.NoticeResponseDto;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.service.NoticeService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
    public List<NoticeResponseDto> getNotices() {
        return noticeService.getNotices();
    }

    //공지사항 작성
    @Operation(summary = "공지사항 작성")
    @PostMapping("/post")
    public NoticeResponseDto createNotice(@RequestBody NoticeRequestDto requestDto) {
        return noticeService.createNotice(requestDto);
    }

    //선택한 공지사항 조회
    @Operation(summary = "선택한 공지사항 조회")
    @GetMapping("/post/{id}")
    public NoticeResponseDto getNotice(@PathVariable Long id) {
        return noticeService.getPost(id);
    }

    //공지사항 수정
    @Operation(summary = "공지사항 수정")
    @PutMapping("/post/{id}")
    public NoticeResponseDto updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto)
            throws Exception {
        return noticeService.updateNotice(id, requestDto);
    }

    //공지사항 삭제
    @Operation(summary = "공지사항 삭제")
    @DeleteMapping("/post/{id}")
    public SuccessResponseDto deleteNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto)
            throws Exception {
        return noticeService.deletePost(id, requestDto);
    }
}
