package com.yi.easycode.modules.user.dto;

import lombok.Data;

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
    private Integer menuParentId;
    /**
     * 是否为目录 目录为0级/其余为1级
     */
    private Integer menuLevel;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单url
     */
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
