package com.practice.demowebapp2.mapper;

import com.practice.demowebapp2.dto.MemberKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface APIKeyMapper {
    List<MemberKey> findApiKeysByMemberId(Integer memberId);
    int insertApiKey(MemberKey memberKey);
}
