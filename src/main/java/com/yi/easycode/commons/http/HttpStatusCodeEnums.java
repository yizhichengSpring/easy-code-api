package com.yi.easycode.commons.http;

/**
 * @author yizhicheng
 * @ClassName HttpStatusCodeEnums
 * @Description http状态码枚举类
 * @Date 2020/10/12 9:30 AM
 **/
public enum HttpStatusCodeEnums {
    /**
     * 成功
     */
    HTTP_OK(HttpStatusCode.HTTP_OK,"成功"),

    /**
     * 拒绝访问
     */
    HTTP_UNAUTHORIZED(HttpStatusCode.HTTP_UNAUTHORIZED,"拒绝访问"),

    /**
     * 找不到资源
     */
    HTTP_NOT_FOUND(HttpStatusCode.HTTP_NOT_FOUND,"找不到资源"),

    /**
     * 服务器内部错误
     */
    HTTP_INTERNAL_ERROR(HttpStatusCode.HTTP_INTERNAL_ERROR,"服务器内部错误"),

    /**
     * 无效响应
     */
    HTTP_BAD_GATEWAY(HttpStatusCode.HTTP_BAD_GATEWAY,"无效响应"),

    /**
     * 接口超时
     */
    HTTP_GATEWAY_TIMEOUT(HttpStatusCode.HTTP_GATEWAY_TIMEOUT,"接口超时");

    /**
     * http code
     */
    private Integer code;

    /**
     * http msg
     */
    private String msg;


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    HttpStatusCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
