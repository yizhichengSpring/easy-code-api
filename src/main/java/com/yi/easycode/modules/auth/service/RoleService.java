package com.yi.easycode.modules.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.dto.BindRoleMenuDTO;
import com.yi.easycode.modules.auth.dto.RoleDTO;
import com.yi.easycode.modules.auth.entity.RoleEntity;
import com.yi.easycode.modules.auth.vo.RoleVO;

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
    PageInfo<RoleVO> getRoleList(String username, Integer pageNum, Integer pageSize);

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

    /**
     * 获取角色码值信息
     * @return
     */
    Result getRoleCode();

    /**
     * 角色绑定菜单信息
     * @param dto
     * @return
     */
    Result bindRoleMenus(BindRoleMenuDTO dto);
}
