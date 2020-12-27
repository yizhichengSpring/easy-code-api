package com.yi.easycode.commons.exception;

import com.yi.easycode.commons.http.HttpStatusCode;
import lombok.Data;

/**
 * @author yizhicheng
 * @ClassName ApiException
 * @Description 自定义异常
 * @Date 2020/10/22 9:46 AM
 **/
@Data
public class ApiException extends RuntimeException {

    private Integer code;

    private String message;

    public ApiException(Integer code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public ApiException(String message) {
        super(message);
        this.code = HttpStatusCode.HTTP_INTERNAL_ERROR;
        this.message = message;
    }

    public ApiException(String message,Exception e) {
        super(message,e);
        this.code = HttpStatusCode.HTTP_INTERNAL_ERROR;
    }
}
