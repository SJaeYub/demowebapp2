package com.practice.demowebapp2.dto;

import lombok.Data;

//@Component
@Data
public class TempUMClient {

    public String tempApiKey;
    public String tempSecretKey;

    public TempUMClient(String tempApiKey, String tempSecretKey) {
        this.tempApiKey = tempApiKey;
        this.tempSecretKey = tempSecretKey;
    }
}