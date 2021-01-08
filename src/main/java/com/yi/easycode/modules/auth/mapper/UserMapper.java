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

    UserEntity getUserInfoByName(String name);

    UserVO getUserInfo(Long userId);
}
