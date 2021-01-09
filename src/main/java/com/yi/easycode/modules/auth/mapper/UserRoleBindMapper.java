package com.yi.easycode.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.UserRoleBindEntity;

/**
 * <p>
 * 用户与角色绑定表 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface UserRoleBindMapper extends BaseMapper<UserRoleBindEntity> {

    void deleteByUserId(Long userId);
}