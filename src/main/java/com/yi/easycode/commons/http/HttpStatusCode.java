package com.yi.easycode.commons.http;

import cn.hutool.http.HttpStatus;

/**
 * @author yizhicheng
 * @ClassName HttpStatusCode
 * @Description http状态码
 * @Date 2020/10/12 9:24 AM
 **/
public class HttpStatusCode {

    /**
     * 当浏览者访问一个网页时，浏览者的浏览器会向网页所在服务器发出请求。当浏览器接收并显示网页前，此网页所在的服务器会返回一个包含HTTP状态码的信息头（server header）用以响应浏览器的请求。
     * https://www.runoob.com/http/http-status-codes.html
     */

    /**
     * 成功 200
     */
    public static final Integer HTTP_OK = HttpStatus.HTTP_OK;

    /**
     * 拒接访问 401
     */
    public static final Integer HTTP_UNAUTHORIZED = HttpStatus.HTTP_UNAUTHORIZED;

    /**
     * 找不到资源 404
     */
    public static final Integer HTTP_NOT_FOUND = HttpStatus.HTTP_NOT_FOUND;

    /**
     * 服务器内部错误 500
     */
    public static final Integer HTTP_INTERNAL_ERROR = HttpStatus.HTTP_INTERNAL_ERROR;

    /**
     * 作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应 502
     */
    public static final Integer HTTP_BAD_GATEWAY = HttpStatus.HTTP_BAD_GATEWAY;

    /**
     * 接口超时 504
     */
    public static final Integer HTTP_GATEWAY_TIMEOUT = HttpStatus.HTTP_GATEWAY_TIMEOUT;
}
