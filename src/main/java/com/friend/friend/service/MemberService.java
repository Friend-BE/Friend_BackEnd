package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.Post;
import com.friend.friend.domain.Qa;
import com.friend.friend.domain.enums.*;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.PostRepository;
import com.friend.friend.repository.QaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final QaRepository qaRepository;

    //회원 가입 기능
    @Transactional //readOnly = false -> default
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); //아이디라도 return 해 줘야 뭐가 저장된지 알 수 있다.
    }

    private void validateDuplicateMember(Member member) {
        //Exception
        Optional<Member> findMembers = memberRepository.findByEmail(member.getEmail());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setNickname(name);
    }
    @Transactional
    public Optional<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }
}
