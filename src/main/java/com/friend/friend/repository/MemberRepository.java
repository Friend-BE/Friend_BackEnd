package com.friend.friend.repository;

import com.friend.friend.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id) { //단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { //jpql 이용해서 전체 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) { //파라미터 이용한 조회 jpql
        return em.createQuery("select m from Member m where m."
                        + "nickname = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
    public Member findByEmail(String email) {
        return em.createQuery("SELECT m FROM Member m WHERE m."
                        + "email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}

