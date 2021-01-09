package com.yi.easycode.commons.annotation;

import java.lang.annotation.*;

/**
 * @author yizhicheng
 * @ClassName LoginLog
 * @Description 登录日志
 * @Date 2021/1/10 12:21 上午
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLog {
}
