package com.friend.friend.jpatest;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.GenderEnum;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateMemberTest() {

        Member member = new Member();
        member.setNickname("채정훈");
        member.setGender(GenderEnum.MALE);

    }
}
