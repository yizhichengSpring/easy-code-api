package com.yi.easycode.modules.user.vo;

import lombok.Data;

import java.io.Serializable;

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

    private String mobile;

    private String email;

    private String createTime;

    private String updateTime;
}
