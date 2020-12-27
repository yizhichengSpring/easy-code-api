package com.yi.easycode.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName Entity
 * @Description 生成代码实体类
 * @Date 2020/12/20 2:58 下午
 **/
@Data
public class GenerateEntity {
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
     * 创建此类时间
     */
    private String createDate;
    /**
     * 类名
     */
    private String className;
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
     * 字段信息
     */
    @JsonIgnore
    private List<ColumnEntity> columns;
    /**
     * 数据库名称
     */
    private String databaseName;
    /**
     * 表名称
     */
    private String tableName;
}
