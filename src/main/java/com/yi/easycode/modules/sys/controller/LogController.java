package com.yi.easycode.modules.sys.controller;

import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import com.yi.easycode.modules.sys.entity.ExceptionLogMongo;
import com.yi.easycode.modules.sys.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yizhicheng
 * @ClassName LoginLogController
 * @Description 登录日志
 * @Date 2021/1/10 1:05 上午
 **/
@RestController
@RequestMapping("/log")
@Api(value = "日志模块",tags = "日志模块")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("loginlist")
    @ApiOperation("登录日志模块")
    public Result getLoginLogList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageResult<LoginLogMongo> pageResult = logService.findLoginLogAll(pageNum, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("exceptionlist")
    @ApiOperation("异常日志模块")
    public Result getExceptionlist(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageResult<ExceptionLogMongo> pageResult = logService.findExceptionLogAll(pageNum, pageSize);
        return Result.success(pageResult);
    }

}
