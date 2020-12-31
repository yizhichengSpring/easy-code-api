package com.yi.easycode.modules.user.controller;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.user.dto.BindUserRoleDTO;
import com.yi.easycode.modules.user.dto.UserDTO;
import com.yi.easycode.modules.user.entity.UserEntity;
import com.yi.easycode.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    
    @ApiOperation("用户绑定角色信息")
    @PostMapping("/bindUserRoles")
    public Result bindUserRoles(@RequestBody @Valid BindUserRoleDTO dto) {
        userService.bindUserRoles(dto);
        return Result.success();
    }

    @ApiOperation("用户绑定角色信息")
    @PostMapping("/info")
    public Result getInfo(HttpServletRequest request) {
        return userService.getInfo(request);
    }
}
