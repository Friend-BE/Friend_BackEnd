package com.friend.friend.service;

import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.dto.MatchingResponseDTO;
import com.friend.friend.dto.MatchingRequestDto;
import com.friend.friend.repository.MatchingRepository;
import com.friend.friend.repository.MemberRepository;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean createMatch(MatchingRequestDto matchingRequestDto, Long userId) {
        try {
            Optional<Member> optionalMember = memberRepository.findById(userId);
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();
                Matching matching = new Matching(matchingRequestDto);
//                matching.setId(member.getId());
                matching.setName(member.getNickname());
                matching.setGender(member.getGender());
                matching.setStatus(MatchingStatusEnum.INCOMPLETE);
                matching.setMember(member);
                matchingRepository.save(matching);
                return true; // 매칭 생성 성공
            } else {
                // 사용자가 존재하지 않는 경우
                return false;
            }
        } catch (Exception e) {
            // 데이터베이스 작업 중 예외 발생 시 처리
            e.printStackTrace();
            return false;
        }
    }

    public List<Matching> getMatchingById(Long id) {
        List<Matching> matching = matchingRepository.findByMember_Id(id);

        if(matching.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 id 입니다");
        }

        return matching;
    }

    /**
     * 신고 가능한 유저는 이미 완료된 상태의 매칭 기록을 불러와야함
     */
    public List<MatchingResponseDTO.getReportListDTO> getReportListById(Long id) {
        Optional<List<Matching>> optionalMatchings = matchingRepository.findByMember_IdAndStatus(id, MatchingStatusEnum.COMPLETE);
        if (optionalMatchings.isPresent()) {
            List<Matching> byMemberIdAndStatus = optionalMatchings.get();
            List<MatchingResponseDTO.getReportListDTO> matchingResponseDTOs = byMemberIdAndStatus.stream()
                    .map(matching -> {
                        Optional<Member> memberOptional = memberRepository.findByNickname(matching.getOpponent());
                        if (memberOptional.isPresent()) {
                            Member member = memberOptional.get();
                            return new MatchingResponseDTO.getReportListDTO(
                                    member.getId(),
                                    matching.getDate(),  // 날짜 정보 추가
                                    member.getNickname(),
                                    member.getBirthday(),  // 상대방 생일 정보 추가
                                    member.getDepartment()
                            );
                        } else {
                            throw new IllegalArgumentException("존재하지 않는 id 입니다");
                        }
                    })
                    .collect(Collectors.toList());
            // matchingResponseDTOs를 처리하거나 반환
            return matchingResponseDTOs;
        } else {
            // 매칭 목록이 없는 경우에 대한 처리
            return Collections.emptyList();
        }
    }

    @Transactional
    public MatchingResponseDTO.makeMatchingDTO makeMatching(Long manId, Long womanId) {
        Optional<List<Matching>> Matching_Man = matchingRepository.findByMember_IdAndStatus(manId, MatchingStatusEnum.INCOMPLETE);
        Optional<List<Matching>> Matching_Woman = matchingRepository.findByMember_IdAndStatus(womanId, MatchingStatusEnum.INCOMPLETE);
        Optional<Member> manMember = memberRepository.findById(manId);
        Optional<Member> womanMember = memberRepository.findById(womanId);
        if (Matching_Man.isPresent()) {
            if (Matching_Woman.isPresent()) {
                if (manMember.isPresent()) {
                    if (womanMember.isPresent()) {
                        Member member = manMember.get();
                        Member member1 = womanMember.get();
                        List<Matching> man = Matching_Man.get();
                        List<Matching> woman = Matching_Woman.get();
                        Matching manMatch = man.get(0);
                        Matching womanMatch = woman.get(0);
                        //상대방 이름 설정
                        manMatch.setOpponent(womanMatch.getName());
                        womanMatch.setOpponent(manMatch.getName());

                        //상대방 생년월일 설정
                        manMatch.setBirthday(member.getBirthday());
                        womanMatch.setBirthday(member1.getBirthday());
                        //매칭 날짜 설정
                        LocalDateTime now = LocalDateTime.now();

                        manMatch.setDate(now);
                        womanMatch.setDate(now);

                        manMatch.setStatus(MatchingStatusEnum.COMPLETE);
                        womanMatch.setStatus(MatchingStatusEnum.COMPLETE);

                        matchingRepository.save(manMatch);
                        matchingRepository.save(womanMatch);

                        MatchingResponseDTO.makeMatchingDTO makeMatchingDTO = new MatchingResponseDTO.makeMatchingDTO();
                        makeMatchingDTO.setManId(member.getId());
                        makeMatchingDTO.setWomanId(member1.getId());
                        makeMatchingDTO.setManPhone(member.getPhone());
                        makeMatchingDTO.setWomanPhone(member1.getPhone());

                        makeMatchingDTO.setDate(now);
                        return makeMatchingDTO;

                    }
                }
            }
        }
        return null;
    }

    public List<MatchingResponseDTO.matchRequestListDTO> findMatchRequest() {
        Optional<List<Matching>> optionalList = matchingRepository.findByStatus(MatchingStatusEnum.INCOMPLETE);
        if(optionalList.isPresent()){
            List<Matching> matchings = optionalList.get();
            List<MatchingResponseDTO.matchRequestListDTO> matchRequestListDTOs = new ArrayList<>();
            for (Matching matching : matchings) {
                MatchingResponseDTO.matchRequestListDTO matchRequestListDTO = new MatchingResponseDTO.matchRequestListDTO();
                matchRequestListDTO.setId(matching.getMember().getId());
                matchRequestListDTO.setNickname(matching.getName());
                matchRequestListDTO.setDate(matching.getCreatedAt());
                matchRequestListDTO.setGender(matching.getGender());

                matchRequestListDTOs.add(matchRequestListDTO);
            }
            return matchRequestListDTOs;
        }else{
            // 매칭 목록이 없는 경우에 대한 처리
            return Collections.emptyList();
        }
    }
}