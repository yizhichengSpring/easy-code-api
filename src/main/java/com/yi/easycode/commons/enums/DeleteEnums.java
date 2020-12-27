package com.yi.easycode.commons.enums;

/**
 * @author yizhicheng
 * @ClassName DeleteEnums
 * @Description 是否删除
 * @Date 2020/10/22 9:55 AM
 **/
public enum  DeleteEnums {
    /**
     * 逻辑删除:0 正常
     */
    NORMAL(0L,"正常"),
    /**
     * 逻辑删除:1 删除
     */
    DEL(1L,"删除");

    private Long code;

    private String value;


    public Long getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    DeleteEnums(Long code, String value) {
        this.code = code;
        this.value = value;
    }
}
