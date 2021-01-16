package com.yi.easycode.modules.generate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.JdbcUtil;
import com.yi.easycode.commons.util.SecretPasswordUtil;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.entity.ColumnEntity;
import com.yi.easycode.modules.generate.entity.DBInfoEntity;
import com.yi.easycode.modules.generate.mapper.DBInfoMapper;
import com.yi.easycode.modules.generate.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName ConnectionServiceImpl
 * @Description 获取数据库相应连接
 * @Date 2020/12/19 7:32 下午
 **/
@Service
@Slf4j
public class DataSourceServiceImpl extends ServiceImpl<DBInfoMapper, DBInfoEntity> implements DataSourceService {

    @Value("${easycodeSecret}")
    private String secretKey;
    
    @Override
    public PageInfo<DBInfoEntity> getAllConnectionList(Integer pageNum, Integer pageSize) {
        QueryWrapper<DBInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        wrapper.orderByDesc("create_time");
        PageHelper.startPage(pageNum,pageSize);
        List<DBInfoEntity> dbInfoEntities = baseMapper.selectList(wrapper);
        return new PageInfo<>(dbInfoEntities);
    }


    @Override
    public Result deleteDataSource(Long id) {
        DBInfoEntity entity = baseMapper.selectById(id);
        entity.setDelFlag(DeleteEnums.DEL.getCode());
        baseMapper.updateById(entity);
        return Result.success();
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
            DBInfoEntity entity = new DBInfoEntity();
            BeanUtils.copyProperties(dto, entity);
            String secretPassword = SecretPasswordUtil.encryptHex(secretKey,entity.getPassword());
            entity.setPassword(secretPassword);
            baseMapper.insert(entity);
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

    /**
     * 目前写死，只有一个MySQL
     * @return
     */
    @Override
    public List<SelectVO> getDataSourceType() {
        List<SelectVO> selectedList = new ArrayList<>();
        SelectVO selectVO = new SelectVO();
        selectVO.setSelectCode("0");
        selectVO.setSelectValue("MySQL");
        selectedList.add(selectVO);
        return selectedList;
    }


    @Override
    public List<SelectVO> getAllSchemas(DatabaseDTO dto) {
        List<SelectVO> selectList = new ArrayList<>();
       try {
           DatabaseMetaData metaData = JdbcUtil.getMetaData(dto);
           if (null == metaData) {
               return selectList;
           }
           ResultSet schemas = metaData.getCatalogs();
           while (schemas.next()) {
               String tableSchema = schemas.getString("TABLE_CAT");
               SelectVO selectVO = new SelectVO();
               selectVO.setSelectCode(tableSchema);
               selectVO.setSelectValue(tableSchema);
               selectList.add(selectVO);
           }
           log.info("该MySQL连接下存在的Schemas:");
           selectList.stream().forEach(x -> log.info(x.getSelectCode()));
           return selectList;
       }catch (SQLException e) {
           log.error("error getAllSchemas {}",e);
       }
       return selectList;
    }
}
