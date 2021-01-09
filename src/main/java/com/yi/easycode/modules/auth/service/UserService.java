package com.yi.easycode.modules.auth.service;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.dto.BindUserRoleDTO;
import com.yi.easycode.modules.auth.dto.UserDTO;
import com.yi.easycode.modules.auth.entity.UserEntity;
import com.yi.easycode.modules.auth.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

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
    UserVO register(UserDTO userDTO);

    /**
     * 登录
     * @param userDTO
     * @return
     */
    Result login(UserDTO userDTO);

    /**
     * 注销
     * @param
     * @return
     */
    Boolean loginOut(HttpServletRequest request);

    /**
     * 获取用户信息
     * @param userName
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserVO> getUserList(String userName, Integer pageNum, Integer pageSize);

    /**
     * 用户绑定角色信息
     * @param dto
     * @return
     */
    Result bindUserRoles(BindUserRoleDTO dto);

    /**
     * 用户信息
     * @param request
     */
    Result getInfo(HttpServletRequest request);

    /**
     * 修改用户
     * @param userInfo
     * @return
     */
    UserEntity update(UserDTO userInfo);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Boolean delete(Long userId);
}
