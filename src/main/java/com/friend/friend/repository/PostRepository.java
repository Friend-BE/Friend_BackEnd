package com.friend.friend.repository;


import com.friend.friend.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}