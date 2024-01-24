package com.friend.friend.service;

import com.friend.friend.domain.board.Qa;
import com.friend.friend.dto.QaRequestDTO;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.repository.QaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QaService {

    private final QaRepository qaRepository;

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
