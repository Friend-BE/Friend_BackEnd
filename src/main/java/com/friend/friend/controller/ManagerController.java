package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.dto.ProfileDetailDTO;
import com.friend.friend.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
