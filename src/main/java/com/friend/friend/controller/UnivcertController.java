package com.friend.friend.controller;

import com.friend.friend.dto.MailDTO;
import com.univcert.api.UnivCert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Configuration
@RestController
@RequestMapping("/api/v1")
public class UnivcertController {
    @Value("${univCert.key}")
    private String key;

    @PostMapping("/certify/send")
    public ResponseEntity<String> sendUnivCertMail(@RequestBody MailDTO mailDTO) throws IOException {
        UnivCert.clear(key, mailDTO.getEmail());
        boolean univ_check = false;
        Map<String, Object> check = UnivCert.check(mailDTO.getUnivName());
        boolean success = (boolean) check.get("success");
        System.out.println("success = " + success);
        if (success) {
            univ_check = true;
        }
        Map<String, Object> result = UnivCert.certify(key, mailDTO.getEmail(), mailDTO.getUnivName(), univ_check);

        boolean emailSuccess = (boolean) result.get("success");

        if (emailSuccess) {
            // 여기에서 이메일이 성공적으로 보내진 경우의 추가 로직을 수행할 수 있습니다.
            // 예를 들어, userMailService.updateUniv(mailDTO.getUnivName());
            return ResponseEntity.ok("이메일이 성공적으로 보내졌습니다.");
        } else {
            return ResponseEntity.badRequest().body("이메일 보내기에 실패했습니다.");
        }
    }

    @GetMapping("/certify/verify")
    public ResponseEntity<String> validMailCode(@RequestParam String email,
                                                @RequestParam String univName,
                                                @RequestParam int code) throws IOException {

        Map<String, Object> response = UnivCert.certifyCode(key, email, univName, code);
        boolean success = (boolean) response.get("success");

        if(success) {
            // userMailService.updateUnivVerify();
        }

        return ResponseEntity.ok(success ? "검증이 성공했습니다" : "검증이 실패했습니다");
    }
}
