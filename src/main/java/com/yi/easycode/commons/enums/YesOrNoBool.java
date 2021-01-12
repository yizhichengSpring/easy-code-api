package com.yi.easycode.commons.enums;

/**
 * @author yizhicheng
 * @ClassName YesOrNoBool
 * @Description Yes、No枚举类
 * @Date 2020/10/12 9:40 AM
 **/
public enum YesOrNoBool {

    /**
     * YES
     */
    YES("YES",true),

    /**
     * NO
     */
    NO("NO",false);

    private String code;

    private Boolean msg;

    public String getCode() {
        return code;
    }

    public Boolean getMsg() {
        return msg;
    }

    YesOrNoBool(String code, Boolean msg) {
        this.code = code;
        this.msg = msg;
    }
}
