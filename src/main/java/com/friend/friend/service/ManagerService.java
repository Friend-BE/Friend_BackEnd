package com.friend.friend.service;

import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.ProfileDetailDTO;
import com.friend.friend.repository.MatchingRepository;
import com.friend.friend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService {
    private final MemberRepository memberRepository;
    private final MatchingRepository matchingRepository;


    public ProfileDetailDTO getProfileDetail(Long userId) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            MemberResponseDTO.profileDTO profile = MemberResponseDTO.profileDTO.builder()
                    .height(member.getHeight())
                    .nickname(member.getNickname())
                    .birthday(member.getBirthday())
                    .region(member.getRegion())
                    .department(member.getDepartment())
                    .imgUrl(member.getImgUrl())
                    .distance(member.getDistance())
                    .smoking(member.getSmoking())
                    .drinking(member.getDrinking())
                    .introduction(member.getIntroduction())
                    .phone(member.getPhone())
                    .build();

            ProfileDetailDTO profileDTO = new ProfileDetailDTO();
            profileDTO.setProfile(profile);
            profileDTO.setWarningCount(member.getWarning());
            List<Matching> matchings = matchingRepository.findAllByMember_IdOrderByCreatedAtDesc(userId);
            if(matchings.isEmpty()){
                profileDTO.setMatchDate(null);
                profileDTO.setMatchCompleteCount(0);
                profileDTO.setMatchCount(0);
            }else{
                profileDTO.setMatchDate(matchings.get(0).getDate());
                profileDTO.setMatchCount(matchings.size());
                Optional<List<Matching>> byMemberIdAndStatus = matchingRepository.findByMember_IdAndStatus(userId, MatchingStatusEnum.COMPLETE);
                if(byMemberIdAndStatus.isPresent()){
                    List<Matching> matchingList = byMemberIdAndStatus.get();
                    profileDTO.setMatchCompleteCount(matchingList.size());
                }
            }
            return profileDTO;
        }else{
            return null;
        }
    }
}
