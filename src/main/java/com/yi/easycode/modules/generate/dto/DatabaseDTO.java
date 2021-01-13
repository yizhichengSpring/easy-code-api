package com.yi.easycode.modules.generate.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yizhicheng
 * @ClassName DatabaseDTO
 * @Description 数据库属性
 * @Date 2020/12/19 7:30 下午
 **/
@Data
public class DatabaseDTO {
    /**
     * 连接名
     */
    private String connectionName;
    /**
     * 数据库类型
     */
    @ApiModelProperty("数据库类型")
    private String type;
    /**
     * 数据库url
     */
    @ApiModelProperty("url")
    @NotNull(message = "url不能为空")
    private String url;
    /**
     * 端口号
     */
    @ApiModelProperty("端口号")
    @NotNull(message = "端口号不能为空")
    private String port;
    /**
     * 数据库用户名
     */
    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    private String name;
    /**
     * 数据库密码
     */
    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 端口号
     */
    @ApiModelProperty("数据库名称")
    @NotNull(message = "数据库名称不能为空")
    private String databaseName;

    public DatabaseDTO() {
        this.type = "MySQL";
        this.url = "127.0.0.1";
        this.port = "3306";
        this.databaseName = "yi-erp";
        this.name = "root";
        this.password = "passw0rd";
    }
}
