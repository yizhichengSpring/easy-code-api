package com.yi.easycode.commons.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.yi.easycode.commons.component.EasyCodeMongoTemplate;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.dto.UserDTO;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import com.yi.easycode.modules.sys.entity.ExceptionLogMongo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yizhicheng
 * @ClassName LoginLogAspect
 * @Description 登录日志切面
 * @Date 2021/1/10 12:23 上午
 **/
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private EasyCodeMongoTemplate mongoTemplate;

    @Pointcut("@annotation(com.yi.easycode.commons.annotation.LoginLog)")
    public void saveLog() {
        log.info("记录登录日志，存在mongodb中");
    }


    @Pointcut("execution(* com.yi.easycode.modules.*.controller..*.*(..))")
    public void saveExceptionLogPoinCut() {
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

    @AfterThrowing(value = "saveExceptionLogPoinCut()",throwing = "exception")
    public void saveExceptionLog(JoinPoint joinPoint,Throwable exception) {
        /**
         * 获取request
         */
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        // 反射获取所属类、方法信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取请求的类名:方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        methodName = className + "." + methodName;
        // 请求的参数
        Map<String, String> rtnMap = converMap(request.getParameterMap());
        String paramJson = JSONUtil.toJsonStr(rtnMap);
        //异常名称
        String exceptionName = exception.getClass().getName();
        //异常信息
        String exceptionMessage = convertException(exception);
        //异常Url
        String exceptionUrl = request.getRequestURI();
        //操作ip
        String exceptionIp = request.getRemoteAddr();
        ExceptionLogMongo exceptionLogMongo = new ExceptionLogMongo();
        exceptionLogMongo.setExceptionName(exceptionName);
        exceptionLogMongo.setExceptionMethod(methodName);
        exceptionLogMongo.setExceptionAddress(exceptionIp);
        exceptionLogMongo.setExceptionRequestParam(paramJson);
        exceptionLogMongo.setExceptionRequestUrl(exceptionUrl);
        exceptionLogMongo.setExceptionUserName("admin");
        exceptionLogMongo.setExceptionTime(DateUtil.now());
        exceptionLogMongo.setExceptionMessage(exceptionMessage);
        log.error("错误信息,{}",exceptionLogMongo.toString());
        mongoTemplate.save(exceptionLogMongo);
    }


    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
     }
     
     private String convertException(Throwable e) {
        StringBuilder exceptionLog = new StringBuilder();
        StringBuilder stackTraceLog = new StringBuilder();
         for (StackTraceElement stet : e.getStackTrace()) {
             stackTraceLog.append(stet + "\n");
         }
        exceptionLog
                .append(e.getClass().getName())
                .append(":")
                .append(e.getMessage())
                .append("\n\t")
                .append(stackTraceLog);
        return exceptionLog.toString();
     }
}
