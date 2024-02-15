package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.board.Qa;
import com.friend.friend.dto.QAResponseDTO;
import com.friend.friend.dto.QaRequestDTO;
import com.friend.friend.dto.SuccessResponseDto;
import com.friend.friend.service.QaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.friend.friend.domain.enums.AnswerStatusEnum.INCOMPLETE;
import static com.friend.friend.domain.enums.PrivacyEnum.PRIVATE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QaController {

    private final QaService qaService;
    private final PasswordEncoder passwordEncoder;


    /**
     * QA 모아보기
     * 관리자일때랑 회원일때랑 제목이 보이냐 안보이냐가 갈리던데 로그인구현방식에 따라 달라질듯?
     * -> jwt쓴다면 id을 jwt토큰안에서 꺼내와서 role을 확인하면될듯?
     */
    @Operation(summary = "모든 Q&A 조회")
    @GetMapping("/qas")
    public ResponseEntity getQas(){
        try{
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
                        .updatedAt(qa.getUpdatedAt())
                        .status(qa.getStatus())
                        .build());
            }
            return new ResponseEntity(Response.success(returnQa), HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Qa 자세히보기
     * (Qa 모아보기 - 자세히)
     */
    @Operation(summary = "Q&A 상세 조회")
    @GetMapping("/qa/{qaId}")
    public ResponseEntity getQa(@PathVariable Long qaId, @RequestParam(required = false) String password){
        try {
            Qa qa = qaService.getQa(qaId, password);    //비밀번호 검증 로직 추가
            QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                    .id(qa.getId())
                    .body(qa.getBody())
                    .updatedAt(qa.getUpdatedAt())
                    .title(qa.getTitle())
                    .author(qa.getAuthor())
                    .status(qa.getStatus())
                    .answer(qa.getAnswer())
                    .build();
            if (getQaDTO != null) {
                return new ResponseEntity(Response.success(getQaDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
            }
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 사용자가 Qa 글쓰기
     */
    @Operation(summary = "Q&A 작성")
    @PostMapping("/qa")
    public ResponseEntity writeQa(@RequestBody QaRequestDTO.writeQaDTO writeQaDTO){
        try {
            Qa qa = new Qa();
            qa.setTitle(writeQaDTO.getTitle());
            qa.setPrivacy(writeQaDTO.getPrivacy());
            qa.setBody(writeQaDTO.getBody());
            qa.setAuthor(writeQaDTO.getAuthor());
            if (writeQaDTO.getPassword() != null && writeQaDTO.getPrivacy() == PRIVATE) {
                qa.setPassword(passwordEncoder.encode(writeQaDTO.getPassword())); //비밀번호 암호화
            }
            qa.setStatus(INCOMPLETE);
            qa.setAnswer(null);

            Long qa_id = qaService.saveQa(qa);
            Qa qa2 = qaService.getQa(qa_id);

            QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                    .id(qa2.getId())
                    .body(qa2.getBody())
                    .updatedAt(qa2.getUpdatedAt())
                    .title(qa2.getTitle())
                    .author(qa2.getAuthor())
                    .status(qa.getStatus())
                    .answer(qa.getAnswer())
                    .build();
            if (getQaDTO != null) {
                return new ResponseEntity(Response.success(getQaDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
            }
        }
        catch(IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Qa 수정
     */
    @Operation(summary = "Q&A 수정")
    @PutMapping("/Qa/{qaId}")
    public ResponseEntity updateReview(@RequestBody QaRequestDTO.updateQaDTO updateQaDTO,@PathVariable Long qaId){
        try {
            Qa qa = qaService.updateQa(qaId, updateQaDTO);
            QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                    .id(qa.getId())
                    .body(qa.getBody())
                    .updatedAt(qa.getUpdatedAt())
                    .title(qa.getTitle())
                    .author(qa.getAuthor())
                    .status(qa.getStatus())
                    .answer(qa.getAnswer())
                    .build();
            if (getQaDTO != null) {
                return new ResponseEntity(Response.success(getQaDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
            }
        }catch(IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Qa 삭제
     */
    @Operation(summary = "Q&A 삭제")
    @DeleteMapping("/qa/{qaId}")
    public ResponseEntity deleteNotice(@PathVariable Long qaId) throws Exception{
        try{
            SuccessResponseDto successResponseDto = qaService.deleteQa(qaId);
            return new ResponseEntity(Response.success(successResponseDto),HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Qa 답변하기
     */
    @Operation(summary = "Q&A 답변하기")
    @PatchMapping("/qa/{qaId}")
    public ResponseEntity createQaAnser(@RequestBody String answer, @PathVariable Long qaId){
        try{
            QAResponseDTO.getQaDTO getQaDTO = qaService.answerQa(qaId, answer);
            return new ResponseEntity(Response.success(getQaDTO),HttpStatus.OK);
        }
        catch(IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    /**
     *"Q&A '나의 문의하기' 전체조회
     */
    @Operation(summary = "Q&A '나의 문의하기' 전체조회 ")
    @GetMapping("/myQas/{memberId}")
    public ResponseEntity getMyQas(@PathVariable(name = "memberId") Long id){
        try{
            List<Qa> MyQas = qaService.getAllMyQa(id);
            Iterator<Qa> iteratorQa = MyQas.iterator();
            List<QAResponseDTO.getQasDTO> returnQa = new ArrayList<>();
            while(iteratorQa.hasNext()) {
                Qa qa = iteratorQa.next();
                returnQa.add(QAResponseDTO.getQasDTO.builder()
                        .id(qa.getId())
                        .privacy(qa.getPrivacy())
                        .title(qa.getTitle())
                        .author(qa.getAuthor())
                        .updatedAt(qa.getUpdatedAt())
                        .status(qa.getStatus())
                        .build());
            }
            return new ResponseEntity(Response.success(returnQa), HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * "Q&A '나의 문의하기' 상세 조회
     */
    @Operation(summary = "Q&A '나의 문의하기' 상세 조회")
    @GetMapping("/qa/{qaId}/{memberId}")
    public ResponseEntity getMyQa(@PathVariable Long qaId, @PathVariable Long memberId){
        try {
            Qa qa = qaService.getMyQa(qaId,memberId);
            QAResponseDTO.getQaDTO getQaDTO = QAResponseDTO.getQaDTO.builder()
                    .id(qa.getId())
                    .body(qa.getBody())
                    .updatedAt(qa.getUpdatedAt())
                    .title(qa.getTitle())
                    .author(qa.getAuthor())
                    .status(qa.getStatus())
                    .answer(qa.getAnswer())
                    .build();
            if (getQaDTO != null) {
                return new ResponseEntity(Response.success(getQaDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity(Response.failure(), HttpStatus.BAD_REQUEST);
            }
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(Response.failure(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    
}
