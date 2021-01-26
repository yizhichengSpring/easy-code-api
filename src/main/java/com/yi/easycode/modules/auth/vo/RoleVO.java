package com.yi.easycode.modules.auth.vo;

import com.yi.easycode.modules.auth.entity.MenuEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName RoleVO
 * @Description 角色列表返回值
 * @Date 2021/1/8 9:17 下午
 **/
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 3856417341435508784L;
    
    private Long id;
    
    private String roleName;
    
    private String roleDescribe;
    
    private String createTime;

    private String updateTime;
    
    private Integer delFlag;

    List<MenuEntity> menus;
}
