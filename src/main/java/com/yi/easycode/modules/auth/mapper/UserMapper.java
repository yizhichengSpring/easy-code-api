package com.yi.easycode.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.UserEntity;
import com.yi.easycode.modules.auth.vo.UserVO;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-24
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据name查询用户信息
     * @param name
     * @return
     */
    UserEntity getUserInfoByName(String name);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    UserVO getUserInfo(Long userId);
}
