package com.yi.easycode.modules.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName BindRoleMenuDTO
 * @Description 角色绑定菜单信息
 * @Date 2020/12/31 10:59 上午
 **/
@Data
public class BindRoleMenuDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long roleId;

    /**
     * 角色id
     */
    @NotNull(message = "菜单id不能为空")
    private List<Long> menuIds;
}
