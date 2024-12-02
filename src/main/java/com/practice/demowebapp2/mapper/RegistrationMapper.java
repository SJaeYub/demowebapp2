package com.practice.demowebapp2.mapper;

import com.practice.demowebapp2.dto.RegistrationCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegistrationMapper {

    boolean isRegistrationCodeValid(@Param("code") String code);

    Integer getRegistrationCodeRoleId(@Param("code") String code);

    RegistrationCode findByCode(String code);
}