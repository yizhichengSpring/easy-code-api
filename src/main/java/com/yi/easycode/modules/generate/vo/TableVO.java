package com.yi.easycode.modules.generate.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName TableVO
 * @Description 返回相应数据库里的所有表，方便后续代码生成
 * @Date 2020/12/19 8:15 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableVO implements Serializable {
    /**
     * 表名
     */
    @ApiModelProperty("表名")
    private String tableName;
    /**
     * 表名
     */
    @ApiModelProperty("表备注")
    private String tableRemark;
}
