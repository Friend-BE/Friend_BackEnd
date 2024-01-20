package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.BoardRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /**
     * email을 통해서 member 객체 받아온다.
     */
    public Member getMemberByEmail(String email) {
        List<Member> member = memberRepository.findByEmail(email);

        if(!member.isEmpty()){
            return member.get(0);
        }
        else{
            return null;  //임시로 박아놓음
        }
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
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());

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
    public List<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }
}
