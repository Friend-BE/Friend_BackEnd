package com.friend.friend.controller;

import com.friend.friend.domain.board.Qa;
import com.friend.friend.dto.QAResponseDTO;
import com.friend.friend.dto.QaRequestDTO;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.service.QaService;
import io.swagger.v3.oas.annotations.Operation;
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
     * 관리자일때랑 회원일때랑 제목이 보이냐 안보이냐가 갈리던데 로그인구현방식에 따라 달라질듯?
     * -> jwt쓴다면 id을 jwt토큰안에서 꺼내와서 role을 확인하면될듯?
     */
    @Operation(summary = "Qa 모아보기")
    @GetMapping("/qas")
    public List<QAResponseDTO.getQasDTO> getQas(){
        List<Qa> Qas = qaService.getAllQa();

        Iterator<Qa> iteratorQa = Qas.iterator();
        List<QAResponseDTO.getQasDTO> returnQa = new ArrayList<>();

        while(iteratorQa.hasNext()) {
            Qa qa = iteratorQa.next();
            returnQa.add(QAResponseDTO.getQasDTO.builder()
                    .id(qa.getId())
                    .privacy(qa.getPrivacy())
                    .title(qa.getTitle())
                    .author(qa.getAuthor())
                    .build());
        }
        return returnQa;
    }

    /**
     * Qa 자세히보기
     * (Qa 모아보기 - 자세히)
     */
    @Operation(summary = "Qa 자세히보기(Figma : Qa모아보기 - 자세히")
    @GetMapping("/qa/{qaId}")
    public QAResponseDTO.getQaDTO getQa(@PathVariable Long qaId){
        Qa qa = qaService.getQa(qaId);
        QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                .id(qa.getId())
                .body(qa.getBody())
                .createdAt(qa.getCreatedAt())
                .title(qa.getTitle())
                .author(qa.getAuthor())
                .build();
        return getQaDTO;
    }


    /**
     * 사용자가 Qa 글쓰기
     */
    @Operation(summary = "Qa 생성")
    @PostMapping("/qa")
    public QAResponseDTO.getQaDTO writeQa(@RequestBody QaRequestDTO.writeQaDTO writeQaDTO){
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

        QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                .id(qa2.getId())
                .body(qa2.getBody())
                .createdAt(qa2.getCreatedAt())
                .title(qa2.getTitle())
                .author(qa2.getAuthor())
                .build();

        return getQaDTO;
    }

    /**
     * Qa 수정
     */
    @Operation(summary = "Qa 수정")
    @PutMapping("/Qa/{qaId}")
    public QAResponseDTO.getQaDTO updateReview(@RequestBody QaRequestDTO.updateQaDTO updateQaDTO,@PathVariable Long qaId){
        Qa qa = qaService.updateQa(qaId, updateQaDTO);
        QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                .id(qa.getId())
                .body(qa.getBody())
                .createdAt(qa.getCreatedAt())
                .title(qa.getTitle())
                .author(qa.getAuthor())
                .build();

        return getQaDTO;
    }


    /**
     * Qa 삭제
     */
    @Operation(summary = "Qa 삭제")
    @DeleteMapping("/qa/{qaId}")
    public SuccessResponseDto deleteNotice(@PathVariable Long qaId) {
        return qaService.deleteQa(qaId);
    }





}
