package com.taca.controller.admin;

import cn.springboot.model.auth.User;
import com.taca.action.QueryTaskAction;
import com.taca.busservice.ShoppingBusService;
import com.taca.common.bean.ResultBean;
import com.taca.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/rest")
public class LoginAdminRestController {

    private static final Logger log = LoggerFactory.getLogger(LoginAdminRestController.class);
    @Autowired
    private QueryTaskAction queryTaskAction;

    @RequestMapping(value = "login", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultBean login() {
        return new ResultBean(queryTaskAction.queryUser(1L));
    }


    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "view/login/login";
    }



}
