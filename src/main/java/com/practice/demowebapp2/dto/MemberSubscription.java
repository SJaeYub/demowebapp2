package com.practice.demowebapp2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberSubscription {
    private Integer subscriptionId;
    private Integer memberId;
    private Integer planId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SubscriptionStatus status;

    // Getters and setters
    // Constructor
    // toString method

    public enum SubscriptionStatus {
        ACTIVE, EXPIRED, CANCELLED
    }
}