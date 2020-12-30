package com.yi.easycode.modules.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName BindUserRoleDTO
 * @Description 用户绑定角色信息
 * @Date 2020/12/30 8:28 下午
 **/
@Data
public class BindUserRoleDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private List<Long> roleIds;
}
