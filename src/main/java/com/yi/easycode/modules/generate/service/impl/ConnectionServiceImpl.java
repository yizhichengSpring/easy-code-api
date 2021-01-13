package com.yi.easycode.modules.generate.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yi.easycode.commons.component.EasyCodeMongoTemplate;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.JdbcUtil;
import com.yi.easycode.commons.util.PageListUtil;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.entity.ColumnEntity;
import com.yi.easycode.modules.generate.entity.mongodb.DBInfoMongo;
import com.yi.easycode.modules.generate.service.ConnectionService;
import com.yi.easycode.modules.sys.entity.ExceptionLogMongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EasyCodeMongoTemplate mongoTemplate;

    @Override
    public PageResult<DBInfoMongo> getAllConnectionList(Integer pageNum, Integer pageSize) {
        List<DBInfoMongo> dbInfoMongoList = mongoTemplate.findAll("createTime", ExceptionLogMongo.class);
        PageResult<DBInfoMongo> pageResult = PageListUtil.startPage(pageNum,pageSize,dbInfoMongoList);
        return pageResult;
    }

    @Override
    public Result testConnection(DatabaseDTO dto) {
        try {
            Connection connection = JdbcUtil.getConn(dto);
            closeConnection(connection);
            return Result.success();
        }catch (SQLException e) {
            log.error("数据库连接失败,{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result saveConnection(DatabaseDTO dto) {
        Result result = testConnection(dto);
        if (result.isSuccess()) {
            //保存信息至MongoDB中
            DBInfoMongo dbInfoMongo = new DBInfoMongo();
            BeanUtils.copyProperties(dto, dbInfoMongo);
            dbInfoMongo.setCreateTime(DateUtil.now());
            mongoTemplate.save(dbInfoMongo);
            return Result.success();
        }
        return Result.fail("保存数据库连接失败");
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
