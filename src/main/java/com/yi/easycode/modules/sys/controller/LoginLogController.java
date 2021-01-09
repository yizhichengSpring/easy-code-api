package com.yi.easycode.modules.sys.controller;

import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import com.yi.easycode.modules.sys.service.LoginLogService;
import io.swagger.annotations.Api;
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
@RequestMapping("/loginLog")
@Api(value = "登录日志模块",tags = "登录日志模块")
@Slf4j
public class LoginLogController {
    @Autowired
    private LoginLogService loginService;

    @GetMapping("list")
    public Result getLoginLogList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageResult<LoginLogMongo> pageResult = loginService.findAll(pageNum, pageSize);
        return Result.success(pageResult);
    }

}
