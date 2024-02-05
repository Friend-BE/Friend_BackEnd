package com.friend.friend.matching;

import com.friend.friend.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class matchingTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;
}
