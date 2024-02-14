package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Qa;
import com.friend.friend.domain.enums.MatchingStatusEnum;
import com.friend.friend.dto.MatchingRequestDto;
import com.friend.friend.dto.MatchingResponseDTO;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.QAResponseDTO;
import com.friend.friend.service.MatchingService;
import com.friend.friend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;
    private final MemberService memberService;

    @Operation(summary = "매칭신청")
    @PostMapping("match/{userId}")
    public ResponseEntity matching(@PathVariable Long userId,@RequestBody MatchingRequestDto requestDto) {
        boolean result = matchingService.createMatch(requestDto,userId);
        if (result) {
            return new ResponseEntity<>(Response.success(requestDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 마이페이지 -2 (매칭내역) 조회
     */
    @Operation(summary = "각 유저 별 매칭내역 조회")
    @GetMapping("myPage/matchinglist/{userId}")
    public ResponseEntity getMypage2(@PathVariable Long userId) {
        try {
            List<Matching> matchings = matchingService.getMatchingById(userId);
            Iterator<Matching> iteratorMatching = matchings.iterator();
            List<MatchingResponseDTO.getMatchingDTO> resultDTO = new ArrayList<>();
            while (iteratorMatching.hasNext()) {
                Matching matching = iteratorMatching.next();
                if (matching.getStatus() == MatchingStatusEnum.COMPLETE)
                resultDTO.add(MatchingResponseDTO.getMatchingDTO.builder()
                        .id(matching.getId())
                        .date(matching.getDate())
                                .department(memberService.findMember(matching.getOpponent()))
                        .opponent(matching.getOpponent())
                        .status(matching.getStatus())
                        .bithday(matching.getBirthday())
                        .build());
            }
            return new ResponseEntity(Response.success(resultDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "신고 가능한 유저 출력")
    @GetMapping("report/reportList/{id}")
    public ResponseEntity getReportList(@PathVariable Long id){
        try{
            List<MatchingResponseDTO.getReportListDTO> matchings = matchingService.getReportListById(id);
            return new ResponseEntity(Response.success(matchings),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "관리자-매칭 이어주기")
    @GetMapping("/match/make/{manId}/{womanId}")
    public ResponseEntity makeMatch(@PathVariable Long manId, @PathVariable Long womanId) {
        try{
            MatchingResponseDTO.makeMatchingDTO makeMatchingDTO = matchingService.makeMatching(manId, womanId);
            return new ResponseEntity(Response.success(makeMatchingDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "매칭 신청한 유저 모아보기")
    @GetMapping("match/list")
    public ResponseEntity matchingRequestList(){
        try{
            List<MatchingResponseDTO.matchRequestListDTO> matchRequest = matchingService.findMatchRequest();
            return new ResponseEntity(Response.success(matchRequest),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
}
