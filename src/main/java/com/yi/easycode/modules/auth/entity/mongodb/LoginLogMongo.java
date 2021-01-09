package com.yi.easycode.modules.auth.entity.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName LoginLogMongo
 * @Description 登录实体
 * @Date 2021/1/10 12:42 上午
 **/
@Document(collection="LoginLog")
@Data
public class LoginLogMongo implements Serializable {

    private static final long serialVersionUID = 5606760537370630756L;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 客户端ip地址
     */
    private String addresss;
    
    /**
     * 客户端主机名
     */
    private String localName;
    
    /**
     * 浏览器信息 User-Agent
     */
    private String userAgent;
    
    /**
     * 登录结果
     */
    private Boolean loginResult;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 登录时间
     */
    private String loginTime;


    @Override
    public String toString() {
        return "LoginLogMongo{" +
                "userName='" + userName + '\'' +
                ", addresss='" + addresss + '\'' +
                ", localName='" + localName + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", loginResult=" + loginResult +
                ", errorMessage='" + errorMessage + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
