package com.friend.friend.repository;

import com.friend.friend.domain.board.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;
    public void save(Post post){
        if(post.getId()==null){
            em.persist(post);
        }else{
            em.merge(post);
        }
    }
}