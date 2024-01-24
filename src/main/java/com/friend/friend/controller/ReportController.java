package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.board.Report;
import com.friend.friend.dto.ReportRequestDto;
import com.friend.friend.dto.ReportResponseDto;
import com.friend.friend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "신고글 작성")
    @PostMapping("/report")
    public ResponseEntity writeReport(@RequestBody ReportRequestDto request){
        Report report = reportService.createReport(request);
        if(report!=null){
            return new ResponseEntity(Response.success(report), HttpStatus.OK);
        }else{
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "신고 내역 전체 조회")
    @GetMapping("/reports")
    public ResponseEntity getReportList(){
        List<ReportResponseDto> result = new ArrayList<>();
        List<Report> reports = reportService.getAllReports();

        for(Report report : reports){
            result.add(new ReportResponseDto(report));
        }
        if(!result.isEmpty()){
            return new ResponseEntity(Response.success(result),HttpStatus.OK);
        }else{
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "신고 내역 상세 조회")
    @GetMapping("/report/{id}")
    public ResponseEntity getReport(@PathVariable Long id){
        Report report = reportService.getReport(id);
        if(report!=null){
            return new ResponseEntity(Response.success(report),HttpStatus.OK);
        }else{
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "회원별 신고 내역 조회")
    @GetMapping("/report/user/{id}")
    public ResponseEntity getReportListByMember(@PathVariable Long id){
        List<ReportResponseDto> result = new ArrayList<>();
        List<Report> reports = reportService.getReportsByMember(id);

        for(Report report : reports){
            result.add(new ReportResponseDto(report));
        }
        if(!result.isEmpty()){
            return new ResponseEntity(Response.success(result),HttpStatus.OK);
        }else{
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }
}
