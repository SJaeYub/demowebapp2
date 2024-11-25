package com.practice.demowebapp2.bean;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userId;
    private String password;
    private String email;
}