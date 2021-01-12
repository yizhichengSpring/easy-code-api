package com.yi.easycode.modules.generate.service;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;

/**
 * @author yizhicheng
 * @ClassName ConnectionService
 * @Description 获取数据库相应连接
 * @Date 2020/12/19 7:32 下午
 **/
public interface ConnectionService {
    /**
     * 保存数据库连接
     * @param dto
     * @return
     */
    Result saveConnection(DatabaseDTO dto);

    /**
     * 获取表信息
     * @param tableName
     * @return
     */
    Result getTableColumn(String tableName);
}
