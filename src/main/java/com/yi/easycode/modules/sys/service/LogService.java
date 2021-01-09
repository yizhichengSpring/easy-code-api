package com.yi.easycode.modules.sys.service;

import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import com.yi.easycode.modules.sys.entity.ExceptionLogMongo;

/**
 * @author yizhicheng
 * @ClassName LoginLogService
 * @Description 登录日志service
 * @Date 2021/1/10 1:07 上午
 **/
public interface LogService {

    /**
     * 获取登录日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<LoginLogMongo> findLoginLogAll(Integer pageNum, Integer pageSize);

    /**
     * 获取异常日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<ExceptionLogMongo> findExceptionLogAll(Integer pageNum, Integer pageSize);
}
