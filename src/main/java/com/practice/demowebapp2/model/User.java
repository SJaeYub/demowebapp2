package com.practice.demowebapp2.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userId;
    private String password;
    private String email;
}