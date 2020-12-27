package com.yi.easycode.commons.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yizhicheng
 * @ClassName FailInfo
 * @Description 错误信息
 * @Date 2020/10/12 9:50 AM
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FailInfo {


    private Integer code;

    private String msg;

    /**
     * 1000~9999 为系统内部错误
     * 10000以上 为业务错误
     */

    public static final FailInfo FAIL_REQUEST = new FailInfo(1000,"系统错误，请联系管理员");
    public static final FailInfo FAIL_AUTHENTICATION = new FailInfo(1001,"系统错误，错误的Authentication");
    public static final FailInfo FAIL_USER_NOTFOUND = new FailInfo(10001,"用户不存在");


}
