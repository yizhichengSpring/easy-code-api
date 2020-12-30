package com.yi.easycode.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
@ApiModel(value="TMenu对象", description="菜单表")
public class MenuEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父类id 一级目录统一为0，二级目录的parentId为一级的主键")
    private Integer menuParentId;

    @ApiModelProperty(value = "是否为目录 目录为0级/其余为1级")
    private Integer menuLevel;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单url")
    private String menuUrl;

    @ApiModelProperty(value = "菜单icon")
    private String menuIcon;

    @ApiModelProperty(value = "菜单排序")
    private Integer menuSort;

    @ApiModelProperty(value = "菜单描述")
    private String menuDescribe;

    @ApiModelProperty(value = "是否删除 0:未删除/1:删除")
    private Integer delFlag;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;


}
