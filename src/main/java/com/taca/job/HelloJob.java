package com.taca.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by jetty on 17/9/20.
 */
@Component
public class HelloJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);


    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数


    @Scheduled(cron="*/5 * * * * ?")
    public void alert(){

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(Sender);
//        message.setTo("473816910@qq.com"); //自己给自己发送邮件
//        message.setSubject("主题：简单邮件");
//        message.setText("测试邮件内容");
//        javaMailSender.send(message);
        LOGGER.info("邮件发送成功");
    }
}
