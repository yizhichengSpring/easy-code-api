package com.yi.easycode.modules.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName LoginDTO
 * @Description 登录入参
 * @Date 2021/1/22 9:28 下午
 **/
@Data
public class LoginDTO implements Serializable {

    @NotBlank(message = "用户名")
    private String userName;

    @NotBlank(message = "密码")
    private String password;
}
