package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Report;
import com.friend.friend.domain.enums.AccountStatusEnum;
import com.friend.friend.dto.MemberResponseDTO;
import com.friend.friend.dto.ReportRequestDto;
import com.friend.friend.dto.ReportResponseDto;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.ReportRepository;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    public boolean createReport(ReportRequestDto request){
        Report report = new Report(request);
        reportRepository.save(report);
        return true;
    }
    public List<ReportResponseDto> getAllReports(){
        return reportRepository.findAll().stream().map(ReportResponseDto::new).toList();
    }

    public ReportResponseDto getReport(Long id){
        Optional<Report> reportId = reportRepository.findById(id);

        return reportId.map(ReportResponseDto::new).orElse(null);
    }

    public List<ReportResponseDto> getReportsByMember(Long memberId){
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            List<ReportResponseDto> reports = reportRepository.findAllByAuthor(member.getNickname()).stream()
                    .map(ReportResponseDto::new).toList();
            return reports;
        } else {
            return Collections.emptyList();
        }
    }
    @Transactional
    public MemberResponseDTO.ReportResponseDTO addReportCount(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            if(member.getWarning()+1==3){
                member.setStatus(AccountStatusEnum.SUSPENDED);
            }
            member.setWarning(member.getWarning() + 1);

            memberRepository.save(member);

            MemberResponseDTO.ReportResponseDTO response = MemberResponseDTO.ReportResponseDTO.builder()
                    .memberId(member.getId())
                    .nickname(member.getNickname())
                    .count(member.getWarning())
                    .build();
            return response;
        }else{
            return (MemberResponseDTO.ReportResponseDTO) Collections.emptyList();
        }
    }
}
