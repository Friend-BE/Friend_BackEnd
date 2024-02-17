package com.friend.friend.service;

import com.friend.friend.domain.Member;
import com.friend.friend.domain.board.Qa;
import com.friend.friend.domain.enums.AnswerStatusEnum;
import com.friend.friend.domain.enums.PrivacyEnum;
import com.friend.friend.domain.enums.RoleEnum;
import com.friend.friend.dto.QAResponseDTO;
import com.friend.friend.dto.QaRequestDTO;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.repository.MemberRepository;
import com.friend.friend.repository.QaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QaService {

    private final QaRepository qaRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @Transactional
    public Qa updateQa(Long qaId, QaRequestDTO.updateQaDTO updateQaDTO) {
        Qa qa = qaRepository.findById(qaId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 QA 입니다")
        );
        qa.update(updateQaDTO);
        return qa;
    }

    public List<Qa> getAllQa() {
        List<Qa> Qas = qaRepository.findAll();
        return Qas;
    }

    public List<Qa> getAllMyQa(Long id) {
        Member member = memberService.findOne(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 Member 입니다")
        );

        List<Qa> MyQas = qaRepository.findByAuthor(member.getNickname());

        return MyQas;
    }
    @Transactional
    public Qa getMyQa(Long qaId, Long memberId) {
        Qa qa = qaRepository.findById(qaId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 QA 입니다"));

        Member member = memberService.findOne(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 Member 입니다."));


        if(qa.getAuthor().equals(member.getNickname())) {
            return qa;
        }
        throw new IllegalArgumentException("해당 Member가 쓴 QA가 아닙니다.");
    }


    @Transactional
    public Qa getQa(Long qaId) {
        Qa qa = qaRepository.findById(qaId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 QA 입니다"));

        return qa;

    }

    @Transactional
    public Qa getQa(Long qaId, String password) {
        Qa qa = qaRepository.findById(qaId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 QA 입니다"));

        if(qa.getPrivacy()==PrivacyEnum.PRIVATE) {
            if (passwordEncoder.matches(password, qa.getPassword())) {
                return qa;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }
        return qa;
    }


    @Transactional
    public QAResponseDTO.getQaDTO answerQa(Long qaId, String answer,Long memberId) throws IllegalArgumentException{
        Qa qa = getQa(qaId);
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 Member입니다.")
        );

        if(!(member.getRole().equals(RoleEnum.ADMIN))){
            throw new IllegalArgumentException("관리자가 아닙니다");
        }
        qa.setAnswer(answer);
        qa.setStatus(AnswerStatusEnum.COMPLETE);

        QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                .id(qa.getId())
                .body(qa.getBody())
                .updatedAt(qa.getUpdatedAt())
                .title(qa.getTitle())
                .author(qa.getAuthor())
                .status(qa.getStatus())
                .answer(qa.getAnswer())
                .build();

        return getQaDTO;

    }

    @Transactional
    public Long saveQa(Qa qa) {
        Qa saveQa = qaRepository.save(qa);
        return saveQa.getId();
    }

    @Transactional
    public SuccessResponseDto deleteQa(Long id) {
            Qa qa = qaRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 QA 입니다")
            );

            qaRepository.deleteById(id);
            return new SuccessResponseDto(true);
        }

}
