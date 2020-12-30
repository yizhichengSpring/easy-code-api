package com.yi.easycode.modules.generate.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yizhicheng
 * @ClassName BaseDTO
 * @Description 基础dto
 * @Date 2020/12/19 9:17 下午
 **/
@Data
public class BaseDTO {
    /**
     * 当前页码
     */
    @ApiModelProperty("当前页面")
    @NotNull(message = "当前页面不能为空")
    private Integer pageNum;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    @NotNull(message = "每页数量不能为空")
    private Integer pageSize;
}
