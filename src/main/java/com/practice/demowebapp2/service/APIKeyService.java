package com.practice.demowebapp2.service;

import com.practice.demowebapp2.dto.MemberKey;
import com.practice.demowebapp2.mapper.APIKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIKeyService {

    @Autowired
    private APIKeyMapper apiKeyMapper;

    public List<MemberKey> getApiKeyInfo(Integer memberId) {
        return apiKeyMapper.findApiKeysByMemberId(memberId);
    }

    public boolean addApiKey(MemberKey memberKey) {
        return apiKeyMapper.insertApiKey(memberKey) > 0;
    }
}
