package com.practice.demowebapp2.model;

import lombok.Data;
import org.springframework.stereotype.Component;

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
