package com.yi.easycode.modules.generate.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yi.easycode.commons.component.EasyCodeMongoTemplate;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.*;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.dto.GenerateDTO;
import com.yi.easycode.modules.generate.entity.ColumnEntity;
import com.yi.easycode.modules.generate.entity.DBInfoEntity;
import com.yi.easycode.modules.generate.entity.GenerateEntity;
import com.yi.easycode.modules.generate.entity.mongodb.GenerateLogMongo;
import com.yi.easycode.modules.generate.mapper.DBInfoMapper;
import com.yi.easycode.modules.generate.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private EasyCodeMongoTemplate mongoTemplate;
    @Value("${easycodeSecret}")
    private String easycodeSecret;



    @Override
    public Result generateList(Integer pageNum, Integer pageSize) {
        List<GenerateLogMongo> generateLogMongoList = mongoTemplate.findAll("generateTime",GenerateLogMongo.class);
        PageResult<GenerateLogMongo> pageResult = PageListUtil.startPage(pageNum,pageSize,generateLogMongoList);
        return Result.success(pageResult);
    }

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
    public List<SelectVO> getSchemaByDataSource(Long id) {
        QueryWrapper<DBInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        wrapper.eq("id",id);
        DBInfoEntity dbInfo = dbInfoMapper.selectOne(wrapper);
        String pwd = SecretPasswordUtil.decryptStr(easycodeSecret,dbInfo.getPassword());
        dbInfo.setPassword(pwd);
        DatabaseDTO dbDTO = new DatabaseDTO();
        BeanUtils.copyProperties(dbInfo, dbDTO);
        return DataSourceUtil.getAllTablesBySchema(dbDTO);
    }

    @Override
    public Result generateCode(GenerateDTO generateDTO) {
        List<Map<String, Object>> datas = generateData(generateDTO);
        GenerateUtil.generateCode(datas);
        return Result.success();
    }


    private List<Map<String, Object>> generateData(GenerateDTO generateDTO) {
        DBInfoEntity entity = dbInfoMapper.selectById(generateDTO.getDataSourceId());
        if (null == entity) {
            throw new ApiException("该数据源不存在");
        }
        DatabaseDTO dto = new DatabaseDTO();
        String pwd = SecretPasswordUtil.decryptStr(easycodeSecret,entity.getPassword());
        entity.setPassword(pwd);
        BeanUtils.copyProperties(entity, dto);
        List<Map<String, Object>> datas = new ArrayList<>();
        datas.add(generateEntityData(dto,generateDTO));
        datas.add(generateServiceData(dto,generateDTO));
        datas.add(generateServiceImplData(dto,generateDTO));
        datas.add(generateControllerData(dto,generateDTO));
        return datas;
    }

    private Map<String, Object> generateEntityData(DatabaseDTO dto,GenerateDTO generateDTO) {

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
        generateEntity.setClassName(convertUnderlineColumnToJavaColumn(generateDTO.getTableName()));
        generateEntity.setTableName(convertUnderlineColumnToJavaColumn(generateDTO.getTableName()));
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
        root.put("packageName",generateDTO.getPackageName());
        return root;
    }


    private Map<String, Object> generateServiceData(DatabaseDTO dto,GenerateDTO generateDTO) {
        GenerateEntity generateService = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateService);
        Map<String, Object> root = new HashMap<>(16);
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

    private Map<String, Object> generateServiceImplData(DatabaseDTO dto,GenerateDTO generateDTO) {
        GenerateEntity generateService = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateService);
        Map<String, Object> root = new HashMap<>(16);
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


    private Map<String, Object> generateControllerData(DatabaseDTO dto,GenerateDTO generateDTO) {
        GenerateEntity generateController = new GenerateEntity();
        BeanUtils.copyProperties(generateDTO,generateController);
        Map<String, Object> root = new HashMap<>(16);
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
}
