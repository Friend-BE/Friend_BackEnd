package com.friend.friend.controller;

import com.friend.friend.common.Response;
import com.friend.friend.domain.UnivCertResponse;
import com.friend.friend.domain.enums.HTTPResponseEnum;
import com.friend.friend.dto.MailDTO;
import com.univcert.api.UnivCert;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Configuration
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UnivCertController {
    @Value("${univCert.key}")
    private String key;

    @Operation(summary = "입력받은 사용자 이메일을 바탕으로 인증코드 전송")
    @PostMapping("/certify/send")
    public ResponseEntity sendUnivCertMail(@RequestBody MailDTO mailDTO) throws IOException {
        UnivCert.clear(key, mailDTO.getEmail());
        Map<String, Object> check = UnivCert.check("부경대학교");
        boolean univ_check = (boolean) check.get("success");

        Map<String, Object> result = UnivCert.certify(key, mailDTO.getEmail(), "부경대학교", univ_check);
        boolean emailSuccess = (boolean) result.get("success");

        UnivCertResponse message = new UnivCertResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if (emailSuccess) {
            message.setStatus(HTTPResponseEnum.OK);
            message.setMessage("Success at sending mail");
            return new ResponseEntity<>(Response.success(message),HttpStatus.OK);
        } else {
            message.setStatus(HTTPResponseEnum.BAD_REQUEST);
            message.setMessage("Error at sending mail");
            return new ResponseEntity<>(Response.failure(),HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "입력받은 사용자 이메일과 인증코드를 바탕으로 이메일 인증")
    @GetMapping("/certify/verify")
    public ResponseEntity validMailCode(@RequestParam String email,
                                                          @RequestParam int code) throws IOException {

        Map<String, Object> response = UnivCert.certifyCode(key, email, "부경대학교", code);
        boolean success = (boolean) response.get("success");

        UnivCertResponse message = new UnivCertResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if (success) {
            message.setStatus(HTTPResponseEnum.OK);
            message.setMessage("Email verification succeeded.");
            return new ResponseEntity<>(Response.success(message),HttpStatus.OK);
        } else {
            message.setStatus(HTTPResponseEnum.BAD_REQUEST);
            message.setMessage("Email verification failed.");
            return new ResponseEntity<>(Response.failure(),HttpStatus.BAD_REQUEST);
        }
    }

}
