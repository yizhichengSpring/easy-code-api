package com.yi.easycode.commons.result;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName PageResult
 * @Description 分页返回值
 * @Date 2020/10/14 4:22 PM
 **/
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 9060678093146773552L;

    /**
     * 由于PageHelper里的PageInfo 字段太多，我们这里只选择需要的几个即可
     */

    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private List<T> list;
    private Long total;


    public static <T> PageResult<T> convert(PageInfo<T> pageInfo) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setSize(pageInfo.getSize());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(pageInfo.getList());
        return pageResult;
    }
}
