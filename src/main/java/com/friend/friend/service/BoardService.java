package com.friend.friend.service;

import com.friend.friend.domain.board.Post;
import com.friend.friend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void savePost(Post post){
        boardRepository.save(post);
    }
}
