package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.Matching;
import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Qa;
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

    /**
     * 마이페이지 -2 (매칭내역) 조회
     */
    @Operation(summary = "매칭내역 조회")
    @GetMapping("myPage/matchinglist/{userId}")
    public ResponseEntity getMypage2(@PathVariable Long userId){
        try {
            List<Matching> matchings = matchingService.getMatchingById(userId);
            Iterator<Matching> iteratorMatching = matchings.iterator();
            List<MatchingResponseDTO.getMatchingDTO> resultDTO = new ArrayList<>();
            while (iteratorMatching.hasNext()) {
                Matching matching = iteratorMatching.next();
                resultDTO.add(MatchingResponseDTO.getMatchingDTO.builder()
                        .id(matching.getId())
                        .date(matching.getDate())
                        .opponent(matching.getOpponent())
                        .status(matching.getStatus())
                        .bithday(matching.getBirthday())
                        .build());
            }
            return new ResponseEntity(Response.success(resultDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
//    @Operation(summary = "신고 가능한 유저 출력")
//    @GetMapping("report/{userId}")
//    public ResponseEntity getReportList(@PathVariable Long userId){
//        try{
//            List<Matching> matchings = matchingService.getReportListById(userId);
//            Iterator<Matching> iteratorMatching = matchings.iterator();
//            List<MatchingResponseDTO.getReportListDTO> resultDTO = new ArrayList<>();
//            while(iteratorMatching.hasNext()){
//                Matching matching = iteratorMatching.next();
//                resultDTO.add(MatchingResponseDTO.getReportListDTO.builder()
//                                .id(matching.getId())
//                                .date(matching.getDate())
//                                .opponent(matching.getOpponent())
//                                .build()
//                );
//            }
//            return new ResponseEntity(Response.success(resultDTO),HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
//        }
//    }
}
