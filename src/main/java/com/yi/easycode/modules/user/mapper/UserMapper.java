package com.yi.easycode.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.user.entity.UserEntity;
import com.yi.easycode.modules.user.vo.UserVO;

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

    UserVO getUserInfo(Integer userId);
}
