package com.friend.friend.service;

import com.friend.friend.domain.board.Qa;
import com.friend.friend.domain.enums.PrivacyEnum;
import com.friend.friend.dto.QAResponseDTO;
import com.friend.friend.dto.QaRequestDTO;
import com.friend.friend.dto.SuccessResponseDto;
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
    private final PasswordEncoder passwordEncoder;
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
    public QAResponseDTO.getQaDTO answerQa(Long qaId, String answer){
        Qa qa = getQa(qaId);
        qa.setAnswer(answer);

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
