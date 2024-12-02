package com.practice.demowebapp2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegistrationMapper {

    boolean isRegistrationCodeValid(@Param("code") String code);

    Integer getRegistrationCodeRoleId(@Param("code") String code);

}