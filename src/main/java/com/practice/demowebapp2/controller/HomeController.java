package com.practice.demowebapp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

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
}