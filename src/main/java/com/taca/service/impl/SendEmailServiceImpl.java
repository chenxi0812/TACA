package com.taca.service.impl;

import com.taca.common.bean.ResultBean;
import com.taca.common.constants.IMResp;
import com.taca.common.exception.BusinessException;
import com.taca.controller.mobile.LoginMobileController;
import com.taca.service.SendEmailService;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by 芳华 on 2017/10/10.
 */
@Component("loginService")
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数

    private static final Logger log = LoggerFactory.getLogger(LoginMobileController.class);

    @Override
    public ResultBean sendEmail(String title,String content,String receiver) {
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,false,"utf-8");
            helper.setTo(receiver);
            helper.setSubject(title);
            helper.setText(content,true);
            helper.setFrom(Sender);
        }
        catch (MessagingException e){
            log.info("邮件发送失败",e);
            throw new BusinessException(IMResp.FAIL);
        }
        javaMailSenderImpl.send(message);
        return new ResultBean(IMResp.SUCCESS);
    }
    @Override
    public ResultBean sendGroupEmail(String title,String content,String[] receiver) {
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,false,"utf-8");
            helper.setTo(receiver);
            helper.setSubject(title);
            helper.setText(content,true);
            helper.setFrom(Sender);
        }
        catch (MessagingException e){
            log.info("邮件发送失败",e);
            throw new BusinessException(IMResp.FAIL);
        }
        javaMailSenderImpl.send(message);
        return new ResultBean(IMResp.SUCCESS);
    }
}