package com.friend.friend.service;

import com.friend.friend.domain.board.Qa;
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

    public List<Qa> getAllQa() {
        List<Qa> Qas = qaRepository.findAll();
        return Qas;
    }

    @Transactional
    public Qa getQa(Long qaId) {
        Optional<Qa> optionalQa = qaRepository.findById(qaId);

        if (!optionalQa.isEmpty()) {
            return optionalQa.get();
        }

        return null;
    }


    public Long saveQa(Qa qa) {
        Qa saveQa = qaRepository.save(qa);
        return saveQa.getId();
    }
}
