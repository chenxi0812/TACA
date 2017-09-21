package com.taca.service.impl;

import com.taca.mapper.UserInfoMapper;
import com.taca.model.UserInfo;
import com.taca.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 17/9/20.
 */

@Component("userService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo getUserById(Integer userId) {
        return userInfoMapper.getUserById(userId);
    }
}
