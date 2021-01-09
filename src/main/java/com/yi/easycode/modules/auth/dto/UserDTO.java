package com.yi.easycode.modules.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName UserDTO
 * @Description 用户dto
 * @Date 2020/12/24 19:15 下午
 **/
@Data
public class UserDTO {

    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Length(max = 20,message = "手机号长度超长")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Length(max = 30,message = "邮箱长度超长")
    private String email;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private List<Long> roleIds;
}
