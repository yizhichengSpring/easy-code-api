package com.yi.easycode.commons.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.PageResult;

import java.util.List;

/**
 * @author yizhicheng
 * @ClassName PageList
 * @Description 当需要对List列表进行分页时，用到这个类
 * @Date 2020/12/19 9:14 下午
 **/
public class PageListUtil {

    public static PageResult startPage(Integer pageNum,Integer pageSize,List<?> list) {
        if (null == pageSize) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        Page page = new Page(pageNum, pageSize);
        int total = list.size();
        page.setTotal(total);
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize,total);
        page.addAll(list.subList(startIndex,endIndex));
        PageInfo<?> pageInfo = new PageInfo<>(page);
        return PageResult.convert(pageInfo);
    }
}
