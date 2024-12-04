package com.practice.demowebapp2.controller;

import com.practice.demowebapp2.dto.Member;
import com.practice.demowebapp2.dto.MemberKey;
import com.practice.demowebapp2.service.APIKeyService;
import com.practice.demowebapp2.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-key")
public class APIKeyController {

    @Autowired
    private APIKeyService apiKeyService;

    @GetMapping("/api-key-info")
    public ResponseEntity<?> getApiKeyInfo(HttpSession session) {
        Member memberInfo = (Member) session.getAttribute("memberInfo");
        Integer memberId = memberInfo.getMemberId();
        List<MemberKey> memberKeys = apiKeyService.getApiKeyInfo(memberId);
        return ResponseEntity.ok(memberKeys);
    }

    @PostMapping("/add-api-key")
    public ResponseEntity<?> addApiKey(@RequestBody MemberKey memberKey, HttpSession session) {
        Member memberInfo = (Member) session.getAttribute("memberInfo");
        Integer memberId = memberInfo.getMemberId();
        memberKey.setMemberId(memberId);
        boolean result = apiKeyService.addApiKey(memberKey);
        if (result) {
            return ResponseEntity.ok("API 키가 성공적으로 추가되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("API 키 추가에 실패했습니다.");
        }
    }
}
