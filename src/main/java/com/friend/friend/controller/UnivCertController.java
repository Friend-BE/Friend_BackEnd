package com.friend.friend.controller;

import com.friend.friend.domain.UnivCertResponse;
import com.friend.friend.domain.enums.HTTPResponseEnum;
import com.friend.friend.dto.MailDTO;
import com.univcert.api.UnivCert;
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
@RequestMapping("/api/v1")
public class UnivCertController {
    @Value("${univCert.key}")
    private String key;

    @PostMapping("/certify/send")
    public ResponseEntity<UnivCertResponse> sendUnivCertMail(@RequestBody MailDTO mailDTO) throws IOException {
        UnivCert.clear(key, mailDTO.getEmail());
        Map<String, Object> check = UnivCert.check(mailDTO.getUnivName());
        boolean univ_check = (boolean) check.get("success");

        Map<String, Object> result = UnivCert.certify(key, mailDTO.getEmail(), mailDTO.getUnivName(), univ_check);
        boolean emailSuccess = (boolean) result.get("success");

        UnivCertResponse message = new UnivCertResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if (emailSuccess) {
            message.setStatus(HTTPResponseEnum.OK);
            message.setMessage("Success at sending mail");
        } else {
            message.setStatus(HTTPResponseEnum.BAD_REQUEST);
            message.setMessage("Error at sending mail");
        }

        return new ResponseEntity<>(message, httpHeaders, emailSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/certify/verify")
    public ResponseEntity<UnivCertResponse> validMailCode(@RequestParam String email,
                                                          @RequestParam String univName,
                                                          @RequestParam int code) throws IOException {

        Map<String, Object> response = UnivCert.certifyCode(key, email, univName, code);
        boolean success = (boolean) response.get("success");

        UnivCertResponse message = new UnivCertResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if (success) {
            message.setStatus(HTTPResponseEnum.OK);
            message.setMessage("Email verification succeeded.");
            return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
        } else {
            message.setStatus(HTTPResponseEnum.BAD_REQUEST);
            message.setMessage("Email verification failed.");
            return new ResponseEntity<>(message, httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }

}
