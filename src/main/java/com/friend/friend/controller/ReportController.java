package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.ReportRequestDto;
import com.friend.friend.dto.ReportResponseDto;
import com.friend.friend.service.ReportService;
import com.google.api.Http;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "신고글 작성")
    @PostMapping("/report")
    public ResponseEntity writeReport(@RequestBody ReportRequestDto request) {
        boolean report = reportService.createReport(request);
        if (report) {
            return new ResponseEntity(Response.success(request), HttpStatus.OK);
        } else {
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "신고 내역 전체 조회")
    @GetMapping("/reports")
    public ResponseEntity getReportList() {
        try {
            List<ReportResponseDto> result = reportService.getAllReports();

            return new ResponseEntity(Response.success(result), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "신고 내역 상세 조회")
    @GetMapping("/report/{id}")
    public ResponseEntity getReport(@PathVariable Long id) {
        ReportResponseDto report = reportService.getReport(id);
        if (report != null) {
            return new ResponseEntity(Response.success(report), HttpStatus.OK);
        } else {
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "회원별 신고 내역 조회")
    @GetMapping("/report/user/{id}")
    public ResponseEntity getReportListByMember(@PathVariable Long id) {
        List<ReportResponseDto> result = reportService.getReportsByMember(id);
        if (result.isEmpty()) {
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(Response.success(result), HttpStatus.OK);
        }
    }
    @Operation(summary = "회원 경고처리")
    @GetMapping("/report/addReportCount/{badMemberid}")
    public ResponseEntity addReportByMember(@PathVariable Long badMemberid){
        try{
            MemberResponseDTO.ReportResponseDTO responseDTO = reportService.addReportCount(badMemberid);
            return new ResponseEntity(Response.success(responseDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "신고 처리하기")
    @PostMapping ("/report/complete")
    public ResponseEntity completeReport(@RequestParam Long id) {
        try {
            ReportResponseDto.completedReportDto responseDTO = reportService.completedReport(id);
            return new ResponseEntity(Response.success(responseDTO), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
        }
    }
}
