package com.practice.demowebapp2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberKey {
    private Integer keyId;
    private Integer memberId;
    private String apiKey;
    private String secKey;
    private KeyStatus status;
    private LocalDateTime createdAt;

    // Getters and setters
    // Constructor
    // toString method

    public enum KeyStatus {
        ACTIVE, INACTIVE
    }
}
