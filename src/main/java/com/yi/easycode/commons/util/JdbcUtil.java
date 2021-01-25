package com.yi.easycode.commons.util;

import cn.hutool.core.util.StrUtil;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.entity.ColumnEntity;
import com.yi.easycode.modules.generate.vo.TableVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName JdbcUtils
 * @Description JDBC工具类
 * @Date 2020/12/20 4:30 下午
 **/
@Slf4j
public class JdbcUtil {
    public static Connection getConn(DatabaseDTO dto) throws SQLException {
           return getConnection(dto);
    }

    public static DatabaseMetaData getMetaData(DatabaseDTO dto) {
        try {
            return getConnection(dto).getMetaData();
        }catch (SQLException e) {
            log.error("error getMetaData",e);
        }
        return null;
    }

    public static DatabaseMetaData getMetaData(Connection conn) {
      try {
          return conn.getMetaData();
      }catch (SQLException e) {
          log.error("error exception",e);
      }
      return null;
    }

    public static List<TableVO> getAllTablesBySchema(DatabaseDTO dto) {
        DatabaseMetaData metaData = getMetaData(dto);
        List<TableVO> tableList = new ArrayList<>();
        try {
            ResultSet res = metaData.getTables(dto.getDatabaseName(),null,null,new String[]{"TABLE"});
            while (res.next()) {
                tableList.add(new TableVO(res.getString("TABLE_NAME"),res.getString("REMARKS")));
            }
        }catch (SQLException e) {
            log.error("error exception",e);
        }
        return tableList;
    }

    public static List<ColumnEntity> getAllTableColumnByTable(DatabaseMetaData metaData, String schemaName, String tableName) {
        List<ColumnEntity> columnEntities = new ArrayList<>();
        try {
            ResultSet res = metaData.getColumns(schemaName,tableName,tableName,"%");
            while (res.next()) {
                ColumnEntity columnEntity = new ColumnEntity();
                columnEntity.setColumnName(res.getString("COLUMN_NAME"));
                columnEntity.setColumnType(res.getString("TYPE_NAME"));
                columnEntity.setColumnLength(res.getString("COLUMN_SIZE"));
                columnEntity.setColumnRemark(res.getString("REMARKS"));
                columnEntities.add(columnEntity);
                log.info("字段名:{},字段类型:{},字段长度:{},字段含义:{}",
                        res.getString("COLUMN_NAME"),
                        res.getString("TYPE_NAME"),
                        res.getString("COLUMN_SIZE"),
                        res.getString("REMARKS"));
            }
        }catch (SQLException e) {
            log.error("error exception",e);
        }
        return columnEntities;
    }

    private static Connection getConnection(DatabaseDTO dto) throws SQLException {
        StringBuilder url = new StringBuilder();
        url.append("jdbc:mysql://")
                .append(dto.getUrl())
                .append(":")
                .append(dto.getPort());

        if (StrUtil.isNotEmpty(dto.getDatabaseName())) {
            url.append("/").append(dto.getDatabaseName());
        }
        log.info("database url,{}",url.toString());
        return DriverManager.getConnection(url.toString(),dto.getName(),dto.getPassword());
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
