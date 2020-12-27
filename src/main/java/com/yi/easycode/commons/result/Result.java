package com.yi.easycode.commons.result;

import com.yi.easycode.commons.enums.YesOrNoBool;
import com.yi.easycode.commons.http.HttpStatusCode;
import com.yi.easycode.commons.http.HttpStatusCodeEnums;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author yizhicheng
 * @ClassName Result
 * @Description 封装统一返回值
 * @Date 2020/10/12 9:14 AM
 **/
@Getter
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private Object data;

    private String msg;

    private boolean success;


    public static Result success(Object data) {
        return new Result(HttpStatusCode.HTTP_OK,data, HttpStatusCodeEnums.HTTP_OK.getMsg(), YesOrNoBool.YES.getMsg());
    }

    public static Result success() {
        return new Result(HttpStatusCode.HTTP_OK, null,HttpStatusCodeEnums.HTTP_OK.getMsg(),YesOrNoBool.YES.getMsg());
    }

    /**
     * 如果你需要自定义code，虽然并不推荐这么做
     * @param code
     * @param data
     * @return
     */
    public static Result success(Integer code,Object data) {
        return new Result(code,data,HttpStatusCodeEnums.HTTP_OK.getMsg(),YesOrNoBool.YES.getMsg());
    }


    public static Result fail() {
        return new Result(HttpStatusCode.HTTP_INTERNAL_ERROR,null,HttpStatusCodeEnums.HTTP_INTERNAL_ERROR.getMsg(),YesOrNoBool.NO.getMsg());
    }


    public static Result fail(String msg) {
        return new Result(HttpStatusCode.HTTP_INTERNAL_ERROR,null,msg,YesOrNoBool.NO.getMsg());
    }

    public static Result fail(Integer code ,String msg) {
        return new Result(code,null,msg,YesOrNoBool.NO.getMsg());
    }
    /**
     * 自定义错误信息
     * @param failInfo
     * @return
     */
    public static Result fail(FailInfo failInfo) {
        return new Result(failInfo);
    }

    /**
     * HTTP_UNAUTHORIZED
     * @return
     */
    public static Result failUnAuthorized() {
        return new Result(HttpStatusCode.HTTP_UNAUTHORIZED,null,HttpStatusCodeEnums.HTTP_UNAUTHORIZED.getMsg(),YesOrNoBool.NO.getMsg());
    }

    /**
     * HTTP_NOT_FOUND
     * @return
     */
    public static Result failNotFound() {
        return new Result(HttpStatusCode.HTTP_NOT_FOUND,null,HttpStatusCodeEnums.HTTP_NOT_FOUND.getMsg(),YesOrNoBool.NO.getMsg());
    }

    /**
     * HTTP_BAD_GATEWAY
     * @return
     */
    public static Result failBadGateWay() {
        return new Result(HttpStatusCode.HTTP_BAD_GATEWAY,null,HttpStatusCodeEnums.HTTP_BAD_GATEWAY.getMsg(),YesOrNoBool.NO.getMsg());
    }

    /**
     * HTTP_GATEWAY_TIMEOUT
     * @return
     */
    public static Result failGateWayTimeOut() {
        return new Result(HttpStatusCode.HTTP_GATEWAY_TIMEOUT,null,HttpStatusCodeEnums.HTTP_GATEWAY_TIMEOUT.getMsg(),YesOrNoBool.NO.getMsg());
    }


    public Result(Integer code, Object data, String msg, boolean success) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = success;
    }
    
    public Result(FailInfo failInfo) {
        this.code = failInfo.getCode();
        this.msg = failInfo.getMsg();
        this.data = null;
        this.success = YesOrNoBool.NO.getMsg();
    }

}
