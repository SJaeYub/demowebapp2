package com.practice.demowebapp2.service;

import com.practice.demowebapp2.controller.MemberController;
import com.practice.demowebapp2.dto.RegistrationCode;
import com.practice.demowebapp2.mapper.RegistrationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CodeService {

    private static final Logger logger = LoggerFactory.getLogger(CodeService.class);

    @Autowired
    private RegistrationMapper registrationMapper;

    public boolean isRegistrationCodeValid(String code) {
        logger.info(code);
        return registrationMapper.isRegistrationCodeValid(code);
    }

    public int getRegistrationCodeRole(String code) {
        return registrationMapper.getRegistrationCodeRoleId(code);
    }

    public boolean validateRegistrationCode(String code) {
        if (isRegistrationCodeValid(code)) {
            int roleName = getRegistrationCodeRole(code);
            System.out.println("유효한 등록 코드입니다. 역할: " + roleName);
            return true;
        } else {
            System.out.println("유효하지 않은 등록 코드입니다.");
            return false;
        }
    }

}
