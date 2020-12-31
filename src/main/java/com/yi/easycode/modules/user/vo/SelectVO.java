package com.yi.easycode.modules.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName RoleVO
 * @Description 角色返回值
 * @Date 2020/12/30 8:20 下午
 **/
@Data
public class SelectVO implements Serializable {

    private static final long serialVersionUID = 7164687687612350516L;

    private String selectCode;
    
    private String selectValue;
}
