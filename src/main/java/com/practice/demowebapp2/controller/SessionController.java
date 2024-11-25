package com.practice.demowebapp2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @GetMapping("/check")
    public boolean checkSession(HttpSession session) {
        return session.getAttribute("userSession") != null;
    }
}