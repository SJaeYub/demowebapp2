package com.practice.demowebapp2.service;

import com.practice.demowebapp2.mapper.RegistrationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.demowebapp2.mapper.MemberMapper;
import com.practice.demowebapp2.dto.Member;

import java.util.List;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RegistrationMapper registrationMapper;

    public boolean registerUser(Member member) {
        if (memberMapper.findByUserId(member.getUserId()) != null) {
            return false;
        }
        logger.info("Registering user engName = " + member.getEnglishName());
        logger.info("Registering user registerCode = " + member.getRegistrationCode());

        int registrationCodeRoleId = registrationMapper.getRegistrationCodeRoleId(member.getRegistrationCode());
        logger.info("Registering user registrationCodeRoleId = " + registrationCodeRoleId);

        member.setRoleId(registrationCodeRoleId);
        memberMapper.save(member);
        return true;
    }

    public boolean authenticateUser(String userId, String password) {
        logger.info("Authenticating user: " + userId);
        Member member = memberMapper.findByUserId(userId);
        logger.info("result = {}", member);
        return member != null && member.getPassword().equals(password);
    }

    public boolean isIdAvailable(String userId) {
        return memberMapper.findByUserId(userId) == null;
    }

    public List<Member> getAllUsers() {
        List<Member> memberList = memberMapper.findAll();
        return memberList;
    }
}