package com.practice.demowebapp2.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubscriptionPlan {
    private Integer planId;
    private String planName;
    private String description;
    private BigDecimal price;
    private Integer duration;

    // Getters and setters
    // Constructor
    // toString method
}