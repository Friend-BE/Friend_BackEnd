package com.friend.friend.service;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.dto.MatchingResponseDTO;
import com.friend.friend.dto.MatchingRequestDto;
import com.friend.friend.dto.MemberResponseDTO;

import com.friend.friend.repository.MatchingRepository;
import com.friend.friend.repository.MemberRepository;
import jakarta.validation.constraints.Null;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public boolean createMatch(MatchingRequestDto matchingRequestDto, Long userId) {
        try {
            Optional<Member> optionalMember = memberRepository.findById(userId);
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();
                Matching matching = new Matching(matchingRequestDto);
                matching.setMember(member);
                matching.setStatus(MatchingStatusEnum.INCOMPLETE);
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
        List<Matching> byMemberIdAndStatus = matchingRepository.findByMember_IdAndStatus(id, MatchingStatusEnum.COMPLETE);

        if (byMemberIdAndStatus.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 id 입니다");
        }

        return byMemberIdAndStatus.stream()
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
    }
}
