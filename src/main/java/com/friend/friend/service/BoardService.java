package com.friend.friend.service;

import com.friend.friend.domain.board.Board;
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
    public Long saveBoard(Board board){
        boardRepository.save(board);
        return board.getId();
    }
    public Board findOne(Long boardId){
        return boardRepository.findOne(boardId);
    }
}
