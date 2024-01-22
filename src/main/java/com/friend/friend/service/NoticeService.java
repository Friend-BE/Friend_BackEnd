package com.friend.friend.service;

import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Notice;
import com.friend.friend.dto.BoardResponseDto;
import com.friend.friend.dto.NoticeRequestDto;
import com.friend.friend.dto.NoticeResponseDto;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> getNotices() {
        return noticeRepository.findAllByOrderByUpdatedAtDesc().stream().map(NoticeResponseDto::new).toList();
    }

    @Transactional
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto) {
        Notice notice = new Notice(requestDto);
        System.out.println(notice);
        noticeRepository.save(notice);
        return new NoticeResponseDto(notice);
    }

    @Transactional
    public NoticeResponseDto getPost(Long id) {
        return noticeRepository.findById(id).map(NoticeResponseDto::new).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디 입니다")
        );
    }

    @Transactional
    public NoticeResponseDto updateNotice(Long id, NoticeRequestDto requestDto) throws Exception {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디 입니다.")
        );

        notice.update(requestDto);
        return new NoticeResponseDto(notice);
    }

    @Transactional
    public SuccessResponseDto deletePost(Long id, NoticeRequestDto requestDto) throws Exception{
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다")
        );

        noticeRepository.deleteById(id);
        return new SuccessResponseDto(true);
    }
}
