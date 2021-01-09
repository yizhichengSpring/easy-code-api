package com.yi.easycode.modules.sys.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName ExceptionLogMongo
 * @Description 异常日志
 * @Date 2021/1/10 2:29 上午
 **/
@Data
@Document(collection="ExceptionLog")
public class ExceptionLogMongo implements Serializable {

    private static final long serialVersionUID = 522547118210600834L;

    /**
     * 请求url
     */
    private String exceptionRequestUrl;

    /**
     * 请求ip
     */
    private String exceptionAddress;

    /**
     * 登录用户
     */
    private String exceptionUserName;

    /**
     * 操作时间
     */
    private String exceptionTime;

    /**
     * 操作方法
     */
    private String exceptionMethod;

    /**
     * 异常名称
     */
    private String exceptionName;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 请求参数
     */
    private String exceptionRequestParam;

    @Override
    public String toString() {
        return "ExceptionLogMongo{" +
                "exceptionRequestUrl='" + exceptionRequestUrl + '\'' +
                ", exceptionAddress='" + exceptionAddress + '\'' +
                ", exceptionUserName='" + exceptionUserName + '\'' +
                ", exceptionTime='" + exceptionTime + '\'' +
                ", exceptionMethod='" + exceptionMethod + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                ", exceptionRequestParam='" + exceptionRequestParam + '\'' +
                '}';
    }
}
