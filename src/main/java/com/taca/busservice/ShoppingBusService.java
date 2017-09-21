package com.taca.busservice;

import com.taca.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jetty on 17/9/20.
 */

@Component("shoppingBusSerice")
public class ShoppingBusService {

    @Autowired
    private UserInfoService userService;

    @Transactional
    public void doShopping(){

    }

}
