package com.yi.easycode.modules.generate.dto;

import lombok.Data;

/**
 * @author yizhicheng
 * @ClassName GenerateDTO
 * @Description 生成code dto
 * @Date 2020/12/27 4:43 下午
 **/
@Data
public class GenerateDTO {
    /**
     * 数据源
     */
    private Integer dataSourceId;
    /**
     * 数据源name
     */
    private Integer dataSourceName;
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
     * 表名称
     */
    private String tableName;
}
