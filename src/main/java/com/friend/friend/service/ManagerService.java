package com.friend.friend.service;

import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.dto.MatchListByDateDTO;
import com.friend.friend.dto.MatchingResponseDTO;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.ProfileDetailDTO;
import com.friend.friend.repository.MatchingRepository;
import com.friend.friend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
                    .preference(member.getPreference())
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

    /**
     * 매칭 완료 내역 보기(날짜별로)
     * 날짜와 매칭 완료 상태 기준으로 모든 데이터 조회
     */
    public List<MatchListByDateDTO> getMatchListByDate(String date) {
        LocalDateTime start = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
        LocalDateTime end = LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")), LocalTime.of(23, 59, 59));

        List<Matching> matchingList = matchingRepository.findAllByStatusAndDateBetween(MatchingStatusEnum.COMPLETE ,start, end);
        HashMap<String, String> hashMap = new HashMap<>();
        List<MatchListByDateDTO> list = new ArrayList<>();
        HashMap<String, LocalDateTime> matchNameTimeMap=new HashMap<>();
        for(int i=0;i<matchingList.size();i++){
            Matching matching = matchingList.get(i);
            if (!hashMap.containsKey(matching.getOpponent())) {
                matchNameTimeMap.put(matching.getName(),matching.getDate());
                hashMap.put(matching.getName(), matching.getOpponent());
            } else {
                continue;
            }
        }
        for (String s : hashMap.keySet()) {
            MatchListByDateDTO dto = new MatchListByDateDTO().builder()
                    .manNickname(s)
                    .womanNickname(hashMap.get(s))
                    .matchDate(matchNameTimeMap.get(s))
                    .build();
            list.add(dto);
        }
        return list;
    }
}
