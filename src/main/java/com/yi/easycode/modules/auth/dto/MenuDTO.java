package com.yi.easycode.modules.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yizhicheng
 * @ClassName MenuDTO
 * @Description 菜单入参
 * @Date 2020/12/31 9:29 上午
 **/
@Data
public class MenuDTO {
    
    private Long id;
    /**
     * 父类id 一级目录统一为0，二级目录的parentId为一级的主键
     */
    @NotBlank(message="上级菜单不能为空")
    private Integer menuParentId;
    /**
     * 是否为目录 目录为0级/其余为1级
     */
    private Integer menuLevel;
    /**
     * 菜单名称
     */
    @NotBlank(message="菜单名称不能为空")
    private String menuName;
    /**
     * 菜单url
     */
    @NotBlank(message="菜单URL不能为空")
    private String menuUrl;
    /**
     * 菜单icon
     */
    private String menuIcon;
    /**
     * 菜单排序
     */
    private Integer menuSort;
    /**
     * 菜单描述
     */
    private String menuDescribe;
}
