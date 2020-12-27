package com.yi.easycode.modules.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yizhicheng
 * @ClassName TableDTO
 * @Description 查询表信息接口需要用到此DTO
 * @Date 2020/12/19 9:19 下午
 **/
@Data
public class TableDTO extends BaseDTO {
    /**
     * 表名
     */
    @ApiModelProperty("表名")
    private String tableName;
}
