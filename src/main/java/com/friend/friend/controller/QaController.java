package com.friend.friend.controller;

import com.friend.friend.domain.board.Qa;
import com.friend.friend.dto.QAResponseDTO;
import com.friend.friend.dto.QaRequestDTO;
import com.friend.friend.service.QaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.friend.friend.domain.enums.AnswerStatusEnum.INCOMPLETE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QaController {

    private final QaService qaService;


    /**
     * QA 모아보기
     * 관리자일때랑 회원일때랑 제목이 보이냐 안보이냐가 갈리던데
     * 로그인구현방식에 따라 달라질듯?
     * jwt쓴다면 id을 jwt토큰안에서 꺼내와서 role을 확인하면될듯?
     */
    @GetMapping("/qas")
    public List<QAResponseDTO.getQaDTO> getQas(){
        List<Qa> Qas = qaService.getAllQa();

        Iterator<Qa> iteratorQa = Qas.iterator();
        List<QAResponseDTO.getQaDTO> returnQa = new ArrayList<>();

        while(iteratorQa.hasNext()) {
            Qa qa = iteratorQa.next();
            returnQa.add(QAResponseDTO.getQaDTO.builder()
                    .id(qa.getId())
                    .privacy(qa.getPrivacy())
                    .title(qa.getTitle())
                    .author(qa.getAuthor())
                    .build());
        }
        return returnQa;
    }

    /**
     * 사용자가 Qa 글쓰기
     */
    @PostMapping("/qa")
    public Qa writeQa(@RequestBody QaRequestDTO.writeQaDTO writeQaDTO){
        Qa qa = new Qa();
        qa.setTitle(writeQaDTO.getTitle());
        qa.setPrivacy(writeQaDTO.getPrivacy());
        qa.setBody(writeQaDTO.getBody());
        qa.setAuthor(writeQaDTO.getAuthor());
        qa.setPassword(writeQaDTO.getPassword());
        qa.setStatus(INCOMPLETE);
        qa.setAnswer(null);

        Long qa_id = qaService.saveQa(qa);
        Qa qa2 = qaService.getQa(qa_id);

        return qa2;
    }

    /**
     * Qa 자세히보기
     * (Qa 모아보기 - 자세히)
     */
    @GetMapping("/qa/{id}")
    public Qa getQa(@PathVariable Long id){
        Qa qa = qaService.getQa(id);
        return qa;
    }




}
