package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.dto.MatchListByDateDTO;
import com.friend.friend.dto.ProfileDetailDTO;
import com.friend.friend.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    @Operation(summary = "관리자페이지-프로필카드 자세히보기")
    @GetMapping("/manager/profileDetail/{userId}")
    public ResponseEntity getProfileDetail(@PathVariable Long userId){
        try{
            ProfileDetailDTO profileDetail = managerService.getProfileDetail(userId);
            return new ResponseEntity(Response.success(profileDetail),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "관리자페이지-(특정날짜 기준)매칭 내역 조회하기")
    @GetMapping("/manager/matchList/{date}")
    public ResponseEntity getMatchListByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String date){
        try{
            List<MatchListByDateDTO> matchListByDate = managerService.getMatchListByDate(date);
            return new ResponseEntity(Response.success(matchListByDate),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
}
