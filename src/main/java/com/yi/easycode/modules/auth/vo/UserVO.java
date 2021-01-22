package com.yi.easycode.modules.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName UserVO
 * @Description 用户VO
 * @Date 2021/1/6 5:49 下午
 **/
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -3246790300462191588L;

    private Long userId;

    private String userName;

    private String password;
    
    private String face;

    private String mobile;

    private String email;

    private String createTime;

    private String updateTime;
    
    private List<String> roleIds;
}
