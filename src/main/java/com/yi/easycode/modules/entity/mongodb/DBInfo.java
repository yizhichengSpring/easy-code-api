package com.yi.easycode.modules.entity.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author yizhicheng
 * @ClassName DBInfo
 * @Description mongodb-存放对应用户的数据库连接
 * @Date 2020/12/27 8:22 下午
 **/
@Document(collection="dbInfo")
@Data
public class DBInfo {
    /**
     * 用户id
     */
    @Indexed
    private Long userId;
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
     * 端口号
     */
    private String databaseName;
}
