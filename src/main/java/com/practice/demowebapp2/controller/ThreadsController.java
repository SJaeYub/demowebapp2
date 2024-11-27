package com.practice.demowebapp2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demowebapp2.model.User;
import com.practice.demowebapp2.service.ThreadsService;
import com.practice.demowebapp2.service.UserService;
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
    private UserService userService;

    @GetMapping("/testuserlist")
    public ResponseEntity<String> testUserData() {
        List<User> userList = userService.getAllUsers();
        List<User> users = threadsService.callThreadsTest(userList);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonUsers = objectMapper.writeValueAsString(users);
            return ResponseEntity.ok()
                    .body(jsonUsers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error converting to JSON");
        }
    }

    @GetMapping("/testdbthreads")
    public ResponseEntity<String> testDbThreads() {
        List<User> userList = userService.getAllUsers();
        threadsService.testServiceThreadsTest(userList);

        return ResponseEntity.ok("ok");
    }
}
