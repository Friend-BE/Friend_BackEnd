package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Qa;
import com.friend.friend.domain.enums.AccountStatusEnum;
import com.friend.friend.domain.enums.GenderEnum;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.repository.MemberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        validateDuplicateMemberByNickname(member);
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
    private void validateDuplicateMemberByNickname(Member member) {
        //Exception
        Optional<Member> findMembers = memberRepository.findByNickname(member.getNickname());
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


    public List<MemberResponseDTO.memberListDTO> memberList(Integer gender, String date){
        LocalDateTime start = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
        LocalDateTime end = LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")), LocalTime.of(23, 59, 59));

        if (gender == 0) {
            return memberRepository.findByStatusAndGenderAndCreatedAtBetween(AccountStatusEnum.ACTIVE, GenderEnum.FEMALE, start, end)
                    .stream().map(MemberResponseDTO.memberListDTO::new).toList();
        } else if (gender == 1){
            return memberRepository.findByStatusAndGenderAndCreatedAtBetween(AccountStatusEnum.ACTIVE, GenderEnum.MALE, start, end)
                    .stream().map(MemberResponseDTO.memberListDTO::new).toList();
        } else {
            throw new IllegalArgumentException("성별은 0 또는 1만 입력 가능합니다.");
        }
    }

    /**
     * email을 이용해서 member 삭제
     */
    @Transactional
    public MemberResponseDTO.successDeleteDTO deleteMember(String email) {

        Member deletedMember = memberRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 멤버 입니다")
        );


        memberRepository.deleteByEmail(email);

        MemberResponseDTO.successDeleteDTO successDeleteMember = MemberResponseDTO.successDeleteDTO.builder()
                .id(deletedMember.getId())
                .success("success")
                .email(deletedMember.getEmail())
                .build();

        return successDeleteMember;
    }

    /**
     * email을 이용해서 member 의 status를 inactive
     */
    @Transactional
    public  MemberResponseDTO.statusInactiveDTO inActiveMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 멤버 입니다")
        );

        member.setStatus(AccountStatusEnum.INACTIVE);

        MemberResponseDTO.statusInactiveDTO statusInactiveDTO = MemberResponseDTO.statusInactiveDTO.builder()
                .id(member.getId())
                .success("success")
                .email(member.getEmail())
                .accountStatusEnum(member.getStatus())
                .build();

        return statusInactiveDTO;

    }

    public String findMember(String opponent) {
        Optional<Member> byNickname = memberRepository.findByNickname(opponent);
        if(byNickname.isPresent()){
            Member member = byNickname.get();
            return member.getNickname();
        }else{
            return null;
        }
    }
}
