package com.yi.easycode.modules.generate.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.ConfigurationUtil;
import com.yi.easycode.commons.util.GenerateUtil;
import com.yi.easycode.commons.util.JdbcUtil;
import com.yi.easycode.commons.util.WordUtil;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.dto.GenerateDTO;
import com.yi.easycode.modules.generate.entity.ColumnEntity;
import com.yi.easycode.modules.generate.entity.DBInfoEntity;
import com.yi.easycode.modules.generate.entity.GenerateEntity;
import com.yi.easycode.modules.generate.mapper.DBInfoMapper;
import com.yi.easycode.modules.generate.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yizhicheng
 * @ClassName GenerateServiceImpl
 * @Description 代码生成ServiceImpl
 * @Date 2020/12/20 1:54 下午
 **/
@Service
@Slf4j
public class GenerateServiceImpl implements GenerateService {
    @Autowired
    private ConfigurationUtil configurationUtil;
    @Autowired
    private DBInfoMapper dbInfoMapper;

    @Override
    public Result datasourceList() {
        QueryWrapper<DBInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        wrapper.orderByDesc("create_time");
        List<DBInfoEntity> dbInfoEntities = dbInfoMapper.selectList(wrapper);
        List<SelectVO> selectvoList = new ArrayList<>();
        dbInfoEntities.stream().forEach( x -> {
            SelectVO selectvo = new SelectVO();
            selectvo.setSelectCode(x.getId().toString());
            selectvo.setSelectValue(x.getConnectionName());
            selectvoList.add(selectvo);
        });
        return Result.success(selectvoList);
    }

    @Override
    public Result generateCode(GenerateDTO generateDTO) {
        List<Map<String, Object>> datas = generateData(generateDTO);
        GenerateUtil.generateCode(datas);
        return Result.success();
    }


    private List<Map<String, Object>> generateData(GenerateDTO generateDTO) {
        List<Map<String, Object>> datas = new ArrayList<>();
        datas.add(generateEntityData(generateDTO));
        datas.add(generateServiceData(generateDTO));
        datas.add(generateServiceImplData(generateDTO));
        datas.add(generateControllerData(generateDTO));
        return datas;
    }

    private Map<String, Object> generateEntityData(GenerateDTO generateDTO) {
        DatabaseDTO dto = new DatabaseDTO();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConn(dto);
        }catch (SQLException e) {
            log.error("error msg {}",e);
        }
        DatabaseMetaData metaData = JdbcUtil.getMetaData(connection);
        List<ColumnEntity> columnEntities = JdbcUtil.getAllTableColumnByTable(metaData,dto.getDatabaseName(),generateDTO.getTableName());
        GenerateEntity generateEntity = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateEntity);
        Map<String, Object> root = new HashMap<>(16);
        generateEntity.setPackageName("com.yi.easycode.modules.entity");
        generateEntity.setOpenLombok(true);
        generateEntity.setOpenSwagger(true);
        generateEntity.setOpenSerializable(true);
        generateEntity.setAuther("yizhicheng");
        generateEntity.setDescription("此类用于测试第一个代码生成器");
        generateEntity.setClassName(convertUnderlineColumnToJavaColumn(generateEntity.getTableName()));
        generateEntity.setTableName(convertUnderlineColumnToJavaColumn(generateEntity.getTableName()));
        generateEntity.setCreateDate(DateUtil.now());
        for (ColumnEntity columnEntity:columnEntities) {
            columnEntity.setColumnType(configurationUtil.getValue(columnEntity.getColumnType().toLowerCase()));
            columnEntity.setColumnName(uncapitalize(convertUnderlineColumnToJavaColumn(columnEntity.getColumnName())));
        }
        generateEntity.setColumns(columnEntities);
        // 将属性集合添加到实体对象中
        root.put("entity", generateEntity);
        root.put("templateName","entity.ftl");
        root.put("className",generateEntity.getClassName());
        root.put("packageName",generateEntity.getPackageName());
        return root;
    }


    private Map<String, Object> generateServiceData(GenerateDTO generateDTO) {
        GenerateEntity generateService = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateService);
        Map<String, Object> root = new HashMap<>(16);
        generateService.setPackageName("com.yi.easycode.modules.service");
        generateService.setOpenLombok(true);
        generateService.setOpenSwagger(true);
        generateService.setOpenSerializable(true);
        generateService.setAuther("yizhicheng");
        generateService.setDescription("此类用于测试第一个代码生成器");
        generateService.setCreateDate(DateUtil.now());
        generateService.setClassName(convertUnderlineColumnToJavaColumn(generateService.getTableName())+"Service");
        generateService.setTableName(convertUnderlineColumnToJavaColumn(generateService.getTableName())+"Service");
        // 将属性集合添加到实体对象中
        root.put("service", generateService);
        root.put("templateName","service.ftl");
        root.put("className",generateService.getClassName());
        root.put("packageName",generateService.getPackageName());
        return root;
    }

    private Map<String, Object> generateServiceImplData(GenerateDTO generateDTO) {
        GenerateEntity generateService = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateService);
        Map<String, Object> root = new HashMap<>(16);
        generateService.setPackageName("com.yi.easycode.modules.service.impl");
        generateService.setOpenLombok(true);
        generateService.setOpenSwagger(true);
        generateService.setOpenSerializable(true);
        generateService.setAuther("yizhicheng");
        generateService.setDescription("此类用于测试第一个代码生成器");
        generateService.setCreateDate(DateUtil.now());
        String tmpClassName = convertUnderlineColumnToJavaColumn(generateService.getTableName());
        generateService.setClassName(tmpClassName+"ServiceImpl");
        generateService.setTableName(tmpClassName+"ServiceImpl");
        // 将属性集合添加到实体对象中
        root.put("serviceImpl", generateService);
        root.put("templateName","serviceImpl.ftl");
        root.put("className",generateService.getClassName());
        root.put("serviceName",tmpClassName+"Service");
        root.put("packageName",generateService.getPackageName());
        return root;
    }


    private Map<String, Object> generateControllerData(GenerateDTO generateDTO) {
        GenerateEntity generateController = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateController);
        Map<String, Object> root = new HashMap<>(16);
        generateController.setPackageName("com.yi.easycode.modules.user.controller");
        generateController.setOpenLombok(true);
        generateController.setOpenSwagger(true);
        generateController.setOpenSerializable(true);
        generateController.setAuther("yizhicheng");
        generateController.setDescription("此类用于测试第一个代码生成器");
        generateController.setCreateDate(DateUtil.now());
        generateController.setClassName(convertUnderlineColumnToJavaColumn(generateController.getTableName())+"Controller");
        generateController.setTableName(convertUnderlineColumnToJavaColumn(generateController.getTableName())+"Controller");
        // 将属性集合添加到实体对象中
        root.put("controller", generateController);
        root.put("templateName","controller.ftl");
        root.put("className",generateController.getClassName());
        root.put("packageName",generateController.getPackageName());
        return root;
    }

    /**
     * 数据库字段(下划线转驼峰，并将下划线后的第一个字母转大写)
     */
    private String convertUnderlineColumnToJavaColumn(String column) {
        return WordUtil.capitalizeFully(column, new char[]{'_'}).replace("_", "" );
    }

    public static String uncapitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0 ? (new StringBuilder(strLen)).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString() : str;
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
