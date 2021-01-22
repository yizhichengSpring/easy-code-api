package com.yi.easycode.modules.sys.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName WhiteUrlDTO
 * @Description 白名单dto
 * @Date 2021/1/19 9:24 下午
 **/
@Data
public class WhiteUrlDTO implements Serializable {
    private static final long serialVersionUID = 4085032199486112653L;

    /**
     * id
     */
    private Long id;
    /**
     * 白名单地址
     */
    @NotBlank(message = "url不能为空")
    private String url;

    /**
     * 请求类型
     */
    @NotBlank(message = "method不能为空")
    private String method;

    /**
     * 备注
     */
    private String remark;
}
