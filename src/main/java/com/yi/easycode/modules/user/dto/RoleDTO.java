package com.yi.easycode.modules.user.dto;

import lombok.Data;

/**
 * @author yizhicheng
 * @ClassName RoleDTO
 * @Description 角色模块入参
 * @Date 2020/12/30 3:22 下午
 **/
@Data
public class RoleDTO {
    /**
     *  id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescribe;
}
