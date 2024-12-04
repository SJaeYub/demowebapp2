package com.practice.demowebapp2.controller;

import com.practice.demowebapp2.dto.Member;
import com.practice.demowebapp2.dto.MemberKey;
import com.practice.demowebapp2.service.CodeService;
import com.practice.demowebapp2.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private CodeService codeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Member member) {
        logger.info("register request received | Member = {}", member);
        if (member.getRegistrationCode().equals("")) {
            member.setRegistrationCode("NORMAL");
        }
        if (memberService.registerUser(member)) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("User ID already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member member, HttpSession session) {
        logger.info("Login request received | Member = {}", member);
        if (memberService.authenticateUser(member.getUserId(), member.getPassword())) {
            Member memberInfo = memberService.getMemberInfo(member.getUserId(), member.getPassword());
            session.setAttribute("userSession", member.getUserId());
            session.setAttribute("memberInfo", memberInfo);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("User logged out successfully");
    }

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkIdAvailability(@RequestParam String userId) {
        return ResponseEntity.ok(memberService.isIdAvailable(userId));
    }

    @GetMapping("/check-code")
    public ResponseEntity<Boolean> checkCodeAvailability(@RequestParam String registerCode) {
        return ResponseEntity.ok(codeService.isRegistrationCodeValid(registerCode));
    }

    @GetMapping("/check-admin")
    public ResponseEntity<Boolean> checkAdminStatus(HttpSession session) {
        Member memberInfo = (Member) session.getAttribute("memberInfo");
        boolean isAdmin = memberService.isAdminUser(memberInfo.getMemberId());
        return ResponseEntity.ok(isAdmin);
    }
}