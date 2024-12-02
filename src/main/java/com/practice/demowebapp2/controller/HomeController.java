package com.practice.demowebapp2.controller;

import com.practice.demowebapp2.dto.Member;
import com.practice.demowebapp2.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("userSession") != null) {
            return "redirect:/welcome";
        }
        return "index";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        if (session.getAttribute("userSession") != null) {
            return "redirect:/welcome";
        }
        return "signup";
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession session) {
        if (session.getAttribute("userSession") == null) {
            return "redirect:/";
        }
        return "welcome";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session) {
        Member memberInfo = (Member) session.getAttribute("memberInfo");
        logger.info("call admin = {}", memberInfo);

        Integer memberId = memberInfo.getMemberId();
        if (memberId == null || !memberService.isAdminUser(memberId)) {
            return "redirect:/";  // 권한이 없으면 메인 페이지로 리다이렉트
        }
        return "admin";  // 관리자 페이지 표시
    }
}