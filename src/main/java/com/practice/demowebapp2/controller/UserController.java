package com.practice.demowebapp2.controller;

import com.practice.demowebapp2.bean.User;
import com.practice.demowebapp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.registerUser(user)) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("User ID already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
        if (userService.authenticateUser(user.getUserId(), user.getPassword())) {
            session.setAttribute("userSession", user.getUserId());
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
        return ResponseEntity.ok(userService.isIdAvailable(userId));
    }
}