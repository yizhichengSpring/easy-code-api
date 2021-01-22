package com.yi.easycode.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.sys.dto.WhiteUrlDTO;
import com.yi.easycode.modules.sys.entity.WhiteUrlEntity;

/**
 * <p>
 * 白名单表 服务类
 * </p>
 *
 * @author yizhicheng
 * @since 2021-01-19
 */
public interface WhiteUrlService extends IService<WhiteUrlEntity> {

    /**
     * 获取白名单数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<WhiteUrlEntity> getWhiteUrlList(Integer pageNum, Integer pageSize);

    /**
     * 新增白名单
     * @param dto
     * @return
     */
    Result addWhiteUrl(WhiteUrlDTO dto);

    /**
     * 修改白名单
     * @param dto
     * @return
     */
    Result updateWhiteUrl(WhiteUrlDTO dto);

    /**
     * 删除白名单
     * @param id
     * @return
     */
    Result deleteWhiteUrl(Long id);

    /**
     * 获取请求方法
     * @return
     */
    Result getRequestMethod();
}
