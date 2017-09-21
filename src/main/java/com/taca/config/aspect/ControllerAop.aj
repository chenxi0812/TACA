package com.taca.config.aspect;

import com.taca.common.bean.ResultBean;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by jetty on 17/9/21.
 */
@Component
@Aspect
@Order(-1)
public aspect ControllerAop {

private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAop.class);


    /**
     * 过滤异常
     * @param pjp
     * @return
     */
    @Pointcut("execution(* com.taca.controller.admin.LoginAdminRestController.login())")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        LOGGER.info("start AOP"+new Date());
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try {
            result = (ResultBean<?>) pjp.proceed();
            LOGGER.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        LOGGER.info("end AOP"+new Date());
        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        LOGGER.error(pjp.getSignature() + " error ", e);
        result.setCode(ResultBean.FAIL);
            result.setMessage(e.getMessage());
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。

        return result;
    }
}