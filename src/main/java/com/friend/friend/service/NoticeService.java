package com.friend.friend.service;

import com.friend.friend.common.Response;
import com.friend.friend.domain.board.Notice;
import com.friend.friend.dto.NoticeRequestDto;
import com.friend.friend.dto.NoticeResponseDto;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.repository.NoticeRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public boolean createNotice(NoticeRequestDto requestDto) {
        Notice notice = new Notice(requestDto);
//        noticeRepository.save(notice);
//        return new NoticeResponseDto(notice);
        if (notice == null) {
            return false;
        } else {
            noticeRepository.save(notice);
            return true;
        }
    }

    @Transactional
    public Optional<NoticeResponseDto> getPost(Long id) {
        return noticeRepository.findById(id).map(NoticeResponseDto::new);
    }

    @Transactional
    public NoticeResponseDto updateNotice(Long id, NoticeRequestDto requestDto) throws Exception{
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다")
        );

        notice.update(requestDto);
        return new NoticeResponseDto(notice);
    }

    @Transactional
    public SuccessResponseDto deletePost(Long id, NoticeRequestDto requestDto) throws Exception {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 입니다")
        );

        noticeRepository.deleteById(id);
        return new SuccessResponseDto(true);
    }
}
