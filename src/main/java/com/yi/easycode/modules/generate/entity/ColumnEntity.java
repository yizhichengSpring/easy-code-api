package com.yi.easycode.modules.generate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yizhicheng
 * @ClassName ColumnEntity
 * @Description 字段实体类
 * @Date 2020/12/20 1:41 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnEntity {
    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段长度
     */
    private String columnLength;

    /**
     * 字段备注
     */
    private String columnRemark;
}
