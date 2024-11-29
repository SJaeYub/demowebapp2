package com.practice.demowebapp2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demowebapp2.dto.Member;
import com.practice.demowebapp2.service.ThreadsService;
import com.practice.demowebapp2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/threads")
public class ThreadsController {

    @Autowired
    private ThreadsService threadsService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/testuserlist")
    public ResponseEntity<String> testUserData() {
        List<Member> memberList = memberService.getAllUsers();
        List<Member> members = threadsService.callThreadsTest(memberList);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonUsers = objectMapper.writeValueAsString(members);
            return ResponseEntity.ok()
                    .body(jsonUsers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error converting to JSON");
        }
    }

    @GetMapping("/testdbthreads")
    public ResponseEntity<String> testDbThreads() {
        List<Member> memberList = memberService.getAllUsers();
        threadsService.testServiceThreadsTest(memberList);

        return ResponseEntity.ok("ok");
    }
}
