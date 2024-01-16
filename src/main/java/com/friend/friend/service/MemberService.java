package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.Post;
import com.friend.friend.domain.Qa;
import com.friend.friend.domain.enums.*;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.PostRepository;
import com.friend.friend.repository.QaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final QaRepository qaRepository;



    //Test용 메서드입니다. 나중에 수정하셔야해요!!
    public void insertMember(){

        Member member = new Member();
        member.setEmail("test@email.com");
        member.setPassword("password1231243124");
        member.setRole(RoleEnum.USER);
        member.setNickname("TestUser");
        member.setPhone("123456789");
        member.setBirthday("1990-01-01");
        member.setGender(GenderEnum.MALE);
        member.setHeight(175.5);
        member.setRegion("TestRegion");
        member.setDepartment("TestDepartment");
        member.setDistance(DistanceEnum.LONG);
        member.setSmoking(SmokingEnum.NONSMOKER);
        member.setDrinking(DrinkingEnum.DRINKER);
        member.setIntroduction("Hello, I am a test user.");
        member.setPreference("Test Preference");
        member.setNondepartment("TestNonDepartment");
        member.setNonstudentid("TestNonStudentId");
        member.setNonage("TestNonAge");
        member.setImage("test_image.jpg"); // 이미지 파일 이름 또는 경로
        member.setStatus(AccountStatusEnum.ACTIVE);

        memberRepository.save(member);

        Member secondMember = new Member();
        secondMember.setEmail("test2@email.com");
        secondMember.setPassword("password567890");
        secondMember.setRole(RoleEnum.USER);
        secondMember.setNickname("TestUser2");
        secondMember.setPhone("987654321");
        secondMember.setBirthday("1995-05-05");
        secondMember.setGender(GenderEnum.FEMALE);
        secondMember.setHeight(160.0);
        secondMember.setRegion("TestRegion2");
        secondMember.setDepartment("TestDepartment2");
        secondMember.setDistance(DistanceEnum.SHORT);
        secondMember.setSmoking(SmokingEnum.SMOKER);
        secondMember.setDrinking(DrinkingEnum.NONDRINKER);
        secondMember.setIntroduction("Hello, I am another test user.");
        secondMember.setPreference("Test Preference 2");
        secondMember.setNondepartment("TestNonDepartment2");
        secondMember.setNonstudentid("TestNonStudentId2");
        secondMember.setNonage("TestNonAge2");
        secondMember.setImage("test_image_2.jpg"); // 이미지 파일 이름 또는 경로
        secondMember.setStatus(AccountStatusEnum.ACTIVE);

        memberRepository.save(secondMember);

        // Qa 게시글 생성
        Qa qa = new Qa();
        qa.setTitle("Sample Question Title");
        qa.setBody("Sample Question Body");
        qa.setStatus(AnswerStatusEnum.INCOMPLETE);
        qa.setPrivacy(PrivacyEnum.PUBLIC);
        qa.setAnswer("Sample Answer");

        // Qa 게시글 저장
        qaRepository.save(qa);

        // Post와 연결
        Post postWithQa = new Post();
        postWithQa.setMember(secondMember);
        postWithQa.setQa(qa);
        postWithQa.setReportid(0);

        // Post 저장
        postRepository.save(postWithQa);
    }

    /**
     * email을 통해서 member 객체 받아온다.
     */
    public Member getMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()){
            return member.get();
        }
        else{
            return null;  //임시로 박아놓음
        }
    }
}
