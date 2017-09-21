package com.taca.mapper;

import com.taca.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jetty on 17/9/20.
 */
@Mapper
public interface UserInfoMapper {

    public UserInfo getUserById(@Param("userId") Integer userId);
}
