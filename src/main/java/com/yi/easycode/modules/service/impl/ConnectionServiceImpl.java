package com.yi.easycode.modules.service.impl;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.dto.DatabaseDTO;
import com.yi.easycode.modules.entity.ColumnEntity;
import com.yi.easycode.modules.service.ConnectionService;
import com.yi.easycode.modules.vo.TableVO;
import com.yi.easycode.util.JdbcUtil;
import com.yi.easycode.util.PageListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName ConnectionServiceImpl
 * @Description 获取数据库相应连接
 * @Date 2020/12/19 7:32 下午
 **/
@Service
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {

    @Override
    public Result saveConnection(DatabaseDTO dto) {
        try {
            Connection connection = JdbcUtil.getConn(dto);
            DatabaseMetaData metaData = JdbcUtil.getMetaData(connection);
            List<TableVO> tableVOS = JdbcUtil.getAllTablesBySchema(metaData,dto.getDatabaseName());
            closeConnection(connection);
            return Result.success(PageListUtil.startPage(null,null,tableVOS));
        }catch (SQLException e) {
            log.error("数据库连接失败,{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    @Override
    public Result getTableColumn(String tableName) {
       try {
           DatabaseDTO dto = new DatabaseDTO();
           Connection connection = JdbcUtil.getConn(dto);
           DatabaseMetaData metaData = JdbcUtil.getMetaData(connection);
           List<ColumnEntity> columnEntities = JdbcUtil.getAllTableColumnByTable(metaData,dto.getDatabaseName(),tableName);
           //释放连接
           closeConnection(connection);
           return Result.success(columnEntities);
       }catch (SQLException e) {
           log.error("查看表字段失败,{}",e.getMessage());
           return Result.fail(e.getMessage());
       }
    }

    /**
     * 释放连接
     * @param conn
     * @throws SQLException
     */
    private void closeConnection(Connection conn) throws SQLException{
        if (null != conn) {
            conn.close();
        }
    }
}
