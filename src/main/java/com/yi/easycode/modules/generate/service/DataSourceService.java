package com.yi.easycode.modules.generate.service;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.entity.DBInfoEntity;

import java.util.List;

/**
 * @author yizhicheng
 * @ClassName ConnectionService
 * @Description 获取数据库相应连接
 * @Date 2020/12/19 7:32 下午
 **/
public interface DataSourceService {

    /**
     * 测试数据库链接
     * @param dto
     * @return
     */
    Result testConnection(DatabaseDTO dto);

    /**
     * 删除数据源
     * @param id
     * @return
     */
    Result deleteDataSource(Long id);

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

    /**
     * 获取数据源列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<DBInfoEntity> getAllConnectionList(Integer pageNum, Integer pageSize);

    /**
     * 获取数据源类型
     * @return
     */
    List<SelectVO> getDataSourceType();

    /**
     * 获取所有schemas
     * @param dto
     * @return
     */
    List<SelectVO> getAllSchemas(DatabaseDTO dto);
}
