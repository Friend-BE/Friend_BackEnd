package com.friend.friend.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.repository.MatchingRepository;
import com.friend.friend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchingService {
    private final MatchingRepository matchingRepository;

    public List<Matching> getMatchingById(Long id) {
        List<Matching> matching = matchingRepository.findByMember_Id(id);

        if(matching.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 id 입니다");
        }

        return matching;
    }
//
//    /**
//     * 신고 가능한 유저는 이미 완료된 상태의 매칭 기록을 불러와야함
//     */
//    public List<Matching> getReportListById(Long id){
//        List<Matching> byMemberIdAndStatus = matchingRepository.findByMember_IdAndStatus(id, MatchingStatusEnum.COMPLETE);
//        if(byMemberIdAndStatus.isEmpty()){
//            throw new IllegalArgumentException("존재하지 않는 id 입니다");
//        }
//
//        return byMemberIdAndStatus;
//    }
}
