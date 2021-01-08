package com.yi.easycode.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.RoleMenuBindEntity;

/**
 * <p>
 * 角色与菜单绑定表 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface RoleMenuBindMapper extends BaseMapper<RoleMenuBindEntity> {

    void deleteByRoleId(Long roleId);
}
