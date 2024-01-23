package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Board;
import com.friend.friend.domain.board.Report;
import com.friend.friend.dto.ReportRequestDto;
import com.friend.friend.repository.BoardRepository;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    public Report createReport(ReportRequestDto request){
        Report report = new Report();
        report.setTitle(request.getTitle());
        report.setBody(request.getBody());
        report.setAuthor(request.getAuthor());
        report.setBadMemberId(request.getBadMemberId());

        reportRepository.save(report);

        return report;
    }
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    public Report getReport(Long id){
        return reportRepository.findById(id).get();
    }

    public List<Report> getReportsByMember(Long memberId){
        String author = memberRepository.findOne(memberId).getNickname();
        return reportRepository.findAllByAuthor(author);
    }
}
