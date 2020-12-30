package com.yi.easycode.modules.user.service;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.modules.user.dto.UserDTO;
import com.yi.easycode.modules.user.entity.UserEntity;

/**
 * @author yizhicheng
 * @ClassName UserService
 * @Description 用户模块
 * @Date 2020/12/24 22:18 下午
 **/
public interface UserService {

    /**
     * 注册接口
     * @param userDTO
     * @return
     */
    UserEntity register(UserDTO userDTO);

    /**
     * 登录
     * @param userDTO
     * @return
     */
    String login(UserDTO userDTO);

    /**
     * 注销
     * @param token
     * @return
     */
    Boolean loginOut(String token);

    /**
     * 获取用户信息
     * @param userName
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserEntity> getUserList(String userName, Integer pageNum, Integer pageSize);
}
