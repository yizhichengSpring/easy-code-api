package com.yi.easycode.modules.generate.entity.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName GenerateLog
 * @Description 生成日志
 * @Date 2021/1/31 3:03 下午
 **/
@Data
@Document(collection="GenerateLog")
public class GenerateLogMongo implements Serializable {
    
    private static final long serialVersionUID = -911635868860354664L;

    /**
     * 数据源
     */
    private String dataSourceName;

    /**
     * 包名
     */
    private String packageName;
    
    /**
     * 作者
     */
    private String auther;
    
    /**
     * 类描述
     */
    private String description;

    /**
     * 是否开启lombok
     */
    private Boolean openLombok;
    
    /**
     * 是否开启swagger
     */
    private Boolean openSwagger;
    
    /**
     * 是否开启序列化
     */
    private Boolean openSerializable;
    
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 生成时间
     */
    private String generateTime;
}
