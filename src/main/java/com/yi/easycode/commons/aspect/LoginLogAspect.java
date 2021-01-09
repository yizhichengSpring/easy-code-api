package com.yi.easycode.commons.aspect;

import cn.hutool.core.date.DateUtil;
import com.yi.easycode.commons.component.EasyCodeMongoTemplate;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.dto.UserDTO;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yizhicheng
 * @ClassName LoginLogAspect
 * @Description 登录日志切面
 * @Date 2021/1/10 12:23 上午
 **/
@Component
@Aspect
@Slf4j
public class LoginLogAspect {

    @Autowired
    private EasyCodeMongoTemplate mongoTemplate;

    @Pointcut("@annotation(com.yi.easycode.commons.annotation.LoginLog)")
    public void saveLog() {
        log.info("记录登录日志，存在mongodb中");
    }

    @Around("saveLog()")
    public Object saveLoginLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("登录日志，开始执行");
        /**
         * 获取登录接口的参数
         */
        UserDTO userDTO = (UserDTO)joinPoint.getArgs()[0];
        /**
         * 获取request
         */
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = (Result)joinPoint.proceed();
        LoginLogMongo logMongo = new LoginLogMongo();
        logMongo.setUserName(userDTO.getUserName());
        // 获取发出请求的客户端的IP地址
        logMongo.setAddresss(request.getRemoteAddr());
        // 获取发出请求的客户端的主机名
        logMongo.setLocalName(request.getLocalName());
        // 获取user-agent
        String userAgent = request.getHeader("User-Agent");
        logMongo.setUserAgent(userAgent);
        logMongo.setLoginResult(result.isSuccess());
        //登录失败，记录错误信息
        if (!result.isSuccess()) {
            logMongo.setErrorMessage(result.getMsg());
        }
        logMongo.setLoginTime(DateUtil.now());
        log.info("完整登录信息,{}",logMongo.toString());
        //保存mongo信息
        mongoTemplate.save(logMongo);
        return result;
    }
}
