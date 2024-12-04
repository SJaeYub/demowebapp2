package com.practice.demowebapp2.mapper;

import com.practice.demowebapp2.dto.Member;
import com.practice.demowebapp2.dto.MemberKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member findByUserId(String userId);
    void save(Member memberDTO);
    List<Member> findAll();
    Member findByIdWithRole(Integer memberId);
}