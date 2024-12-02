package com.practice.demowebapp2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private Integer memberId;
    private String userId;
    private String name;
    private String englishName;
    private String email;
    private String password;
    private String registrationCode;
    private String phone;
    private LocalDateTime joinDate;
    private LocalDateTime leaveDate;
    private MemberStatus status;
    private SubscriptionStatus subscriptionStatus;
    private Integer roleId;

    public enum MemberStatus {
        active, inactive
    }

    public enum SubscriptionStatus {
        subscribed, unsubscribed
    }
}