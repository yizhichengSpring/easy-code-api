package com.yi.easycode.modules.service;

import com.yi.easycode.modules.dto.UserDTO;
import com.yi.easycode.modules.entity.UserEntity;

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
}
