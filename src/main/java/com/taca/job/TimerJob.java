package com.taca.job;

import com.taca.common.bean.ResultBean;
import com.taca.common.constants.IMResp;
import com.taca.common.exception.BusinessException;
import com.taca.service.SendEmailService;
import com.taca.service.UserInfoService;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jetty on 17/9/20.
 */
@Component
public class TimerJob {

    private static final Logger log = LoggerFactory.getLogger(TimerJob.class);

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SendEmailService sendEmailService;

    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数

    @Scheduled(cron = "0 0 10 1 * ?")
    public void noticeGoods(){
        List<String> emailList=userInfoService.getUserEmailList();
        String[] emailArray=new String[emailList.size()];
        emailList.toArray(emailArray);
        String title="卓越职场人：激励兑换通知邮件";
        //读取邮件模板
        Template template=null;
        String content="";
        try {
            template= freeMarkerConfigurer.getConfiguration().getTemplate("/emailTemplates/goodsNotice.ftl");
            Map map=new HashMap();
            map.put("date","1");
            content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        }
        catch (Exception e){
            log.info("邮件读取出错",e);
            throw new BusinessException(IMResp.TEMPLATE_LOAD_ERROR);
        }
        ResultBean resultBean=sendEmailService.sendGroupEmail(title,content,emailArray);
        log.info(resultBean.toString());
    }

}
