package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.AccountStatusEnum;
import com.friend.friend.repository.MemberRepository;

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


    /**
     * email을 통해서 member 객체 받아온다.
     */
    public Member getMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.orElse(null);
    }

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
        findMembers.ifPresent(
                m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                }
        );
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(m -> m.setNickname(name));
    }

    @Transactional
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public void activateAccount(String email) {
        Optional<Member> members = memberRepository.findByEmail(email);
        members.ifPresent(member -> {
            member.setStatus(AccountStatusEnum.ACTIVE);
            memberRepository.save(member);
        });
    }

    public List<Member> findAuditList() {
        return memberRepository.findByStatus(AccountStatusEnum.AUDIT);
    }


}
