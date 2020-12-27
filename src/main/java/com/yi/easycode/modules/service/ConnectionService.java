package com.yi.easycode.modules.service;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.dto.DatabaseDTO;

/**
 * @author yizhicheng
 * @ClassName ConnectionService
 * @Description 获取数据库相应连接
 * @Date 2020/12/19 7:32 下午
 **/
public interface ConnectionService {
    Result saveConnection(DatabaseDTO dto);

    Result getTableColumn(String tableName);
}
