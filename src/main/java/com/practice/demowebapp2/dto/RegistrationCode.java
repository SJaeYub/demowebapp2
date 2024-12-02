package com.practice.demowebapp2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationCode {
    private Integer codeId;
    private String code;
    private Integer roleId;
    private boolean isActive;
    private LocalDateTime createdAt;

    // Getters and setters
    // Constructor
    // toString method
}