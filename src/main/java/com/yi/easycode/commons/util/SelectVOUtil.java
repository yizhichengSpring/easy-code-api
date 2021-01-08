package com.yi.easycode.commons.util;

import com.yi.easycode.modules.auth.vo.SelectVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName SelectVOUtil
 * @Description 初始化下拉列表
 * @Date 2021/1/8 8:11 下午
 **/
public class SelectVOUtil {
    
    public static List<SelectVO> initSelectVOList() {
        List<SelectVO> selectVOList = new ArrayList<>();
        selectVOList.add(new SelectVO("0","无"));
        return selectVOList;
    }
}
