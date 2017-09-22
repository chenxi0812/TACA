package com.taca.action;

import com.taca.model.UserInfo;
import com.taca.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jetty on 17/9/22.
 */
@Component
public class QueryTaskAction {
    @Autowired
    private UserInfoService userInfoService;


    public UserInfo queryUser(Long id){
       return userInfoService.getUserById(id);
    }

}
