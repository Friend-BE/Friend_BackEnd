package com.friend.friend.controller;

import com.friend.friend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;

//    @PostMapping("/savePost")
//    public void savePost(@RequestBody )
}
