package com.taca.service.impl;

import com.taca.common.constants.IMResp;
import com.taca.common.exception.DBException;
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
    public UserInfo getUserById(Long userId) {
        UserInfo userInfo= new UserInfo();
        userInfo.setGroupName("ndadad");
        userInfo.setNickName("shaxiaozi");
//        throw new DBException(IMResp.DBEXCEPTION);
        return userInfo;
    }
}
