package com.yi.easycode.commons.enums;

/**
 * @author yizhicheng
 * @ClassName YesOrNoBool
 * @Description Yes、No枚举类
 * @Date 2020/10/12 9:40 AM
 **/
public enum YesOrNoInteger {
    YES("YES",0),
    NO("NO",1);

    private String code;

    private Integer value;

    public String getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }

    YesOrNoInteger(String code, Integer value) {
        this.code = code;
        this.value = value;
    }
}
