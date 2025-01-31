package com.yi.easycode.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.UserRoleBindEntity;

import java.util.List;

/**
 * <p>
 * 用户与角色绑定表 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface UserRoleBindMapper extends BaseMapper<UserRoleBindEntity> {

    /**
     * 删除用户角色关联关系
     * @param userId
     */
    void deleteByUserId(Long userId);

    /**
     * 根据用户id查询对应角色id
     * @param userId
     * @return
     */
    List<String> getRoleIdsByUserId(Long userId);
}
