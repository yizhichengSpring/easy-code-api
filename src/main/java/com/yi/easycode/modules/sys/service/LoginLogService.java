package com.yi.easycode.modules.sys.service;

import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;

/**
 * @author yizhicheng
 * @ClassName LoginLogService
 * @Description 登录日志service
 * @Date 2021/1/10 1:07 上午
 **/
public interface LoginLogService {

    PageResult<LoginLogMongo> findAll(Integer pageNum,Integer pageSize);
}
