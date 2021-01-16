package com.yi.easycode.modules.generate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yizhicheng
 * @ClassName DBInfoEntity
 * @Description 数据库信息
 * @Date 2021/1/16 2:13 下午
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_db_info")
@ApiModel(value="DBInfo对象", description="数据库信息表")
public class DBInfoEntity {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 连接名
     */
    private String connectionName;
    /**
     * 数据库类型
     */
    private String type;
    /**
     * 数据库url
     */
    private String url;
    /**
     * 端口号
     */
    private String port;
    /**
     * 数据库用户名
     */
    private String name;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库名称
     */
    private String databaseName;
    /**
     * 创建时间
     */
    private String createTime;
}
