package com.yi.easycode.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.user.dto.RoleDTO;
import com.yi.easycode.modules.user.entity.RoleEntity;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface RoleService extends IService<RoleEntity> {

    /**
     * 查询角色列表
     */
    PageInfo<RoleEntity> getRoleList(String username,Integer pageNum,Integer pageSize);

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    Result saveRoleEntity(RoleDTO roleDTO);

    /**
     * 修改角色
     * @param roleDTO
     * @return
     */
    Result updateRoleEntity(RoleDTO roleDTO);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Result deleteRoleEntity(Long id);
}
