package com.friend.friend.repository;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.AccountStatusEnum;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import org.springframework.stereotype.Repository;

import static com.friend.friend.domain.enums.AccountStatusEnum.AUDIT;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;



    public void save(Member member) {
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

    public List<Member> findByEmail(String email) {
        return em.createQuery("SELECT m FROM Member m WHERE m."
                        + "email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
    public void activateAccount(String nickname) {
        try {
            Member member = em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
            member.setStatus(AccountStatusEnum.ACTIVE);
            em.persist(member);
        } catch (NoResultException e) {
        }
    }

    public List<Member> findByStatus() {
        return em.createQuery("SELECT m FROM Member m WHERE m.status = :status", Member.class)
                .setParameter("status",AUDIT)
                .getResultList();
    }

}

