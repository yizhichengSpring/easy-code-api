package com.yi.easycode.modules.controller;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.dto.UserDTO;
import com.yi.easycode.modules.entity.UserEntity;
import com.yi.easycode.modules.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yizhicheng
 * @ClassName LoginController
 * @Description 登录
 * @Date 2020/12/20 20:09 下午
 **/
@RestController
@RequestMapping("/user")
@Api(value = "用户模块",tags = "用户模块")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping(value = "login")
    public Result login(@RequestBody @Valid UserDTO userDTO) {
        String token = userService.login(userDTO);
        return Result.success(token);
    }

    @ApiOperation("注册")
    @PostMapping(value = "register")
    public Result register(@RequestBody @Valid UserDTO userInfo) {
        UserEntity userEntity = userService.register(userInfo);
        return Result.success(userEntity);
    }

    @ApiOperation("注销")
    @PostMapping(value = "loginOut")
    public Result loginOut(String token) {
        return Result.success(userService.loginOut(token));
    }

    @ApiOperation("获取所有用户")
    @PostMapping("/list")
    public Result getUserList(@RequestParam(value = "userName",required = false) String userName,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        PageInfo<UserEntity> pageInfo = userService.getUserList(userName,pageNum,pageSize);
        return Result.success(PageResult.convert(pageInfo));
    }
}
