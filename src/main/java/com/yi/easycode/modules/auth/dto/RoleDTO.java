package com.yi.easycode.modules.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescribe;
    /**
     * 菜单id
     */
    private List<Long> menuIds;
}
