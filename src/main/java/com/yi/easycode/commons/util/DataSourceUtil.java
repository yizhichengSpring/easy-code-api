package com.yi.easycode.commons.util;

import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.vo.TableVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName DataSourceUtil
 * @Description 数据源相关工具类
 * @Date 2021/1/25 9:25 下午
 **/
@Slf4j
public class DataSourceUtil {
    /**
     * 获取所有schema
     */
    public static List<SelectVO> getShemas(DatabaseDTO dto) {
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

    /**
     * 获取某schme下所有表
     * @param dto
     * @return
     */
    public static List<SelectVO> getAllTablesBySchema(DatabaseDTO dto) {
        DatabaseMetaData metaData = JdbcUtil.getMetaData(dto);
        List<TableVO> tableList = new ArrayList<>();
        List<SelectVO> selectvoList = new ArrayList<>();
        try {
            ResultSet res = metaData.getTables(dto.getDatabaseName(),null,null,new String[]{"TABLE"});
            while (res.next()) {
                tableList.add(new TableVO(res.getString("TABLE_NAME"),res.getString("REMARKS")));
            }
        }catch (SQLException e) {
            log.error("error exception",e);
        }
        tableList.stream().forEach(x -> {
            SelectVO selectvo = new SelectVO();
            selectvo.setSelectCode(x.getTableName());
            selectvo.setSelectValue(x.getTableName());
            selectvoList.add(selectvo);
        });
        return selectvoList;
    }
}
