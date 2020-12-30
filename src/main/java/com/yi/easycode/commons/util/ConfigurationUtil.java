package com.yi.easycode.commons.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @author yizhicheng
 * @ClassName ConfigurationUtil
 * @Description 获取对应配置文件
 * @Date 2020/12/20 3:49 下午
 **/
@Component
@PropertySource(value = "classpath:mysql-generator.properties")
@Data
@Slf4j
public class ConfigurationUtil {

    @Value("${column_inyint}")
    private String inyIntColumn;

    @Value("${column_int}")
    private String intColumn;

    @Value("${column_integer}")
    private String integerColumn;

    @Value("${column_bigint}")
    private String bigintColumn;

    @Value("${column_bit}")
    private String bitColumn;

    @Value("${column_float}")
    private String floatColumn;

    @Value("${column_double}")
    private String doubleColumn;

    @Value("${column_decimal}")
    private String decimalColumn;

    @Value("${column_char}")
    private String charColumn;

    @Value("${column_varchar}")
    private String varcharColumn;

    @Value("${column_date}")
    private String dateColumn;

    @Value("${column_datetime}")
    private String datetimeColumn;

    @Value("${column_timestamp}")
    private String timestampColumn;


    public Map<String, String> generateReflectColumns() {
        Map<String, String> map = new HashMap<>(20);
        map.put("inyInt",getInyIntColumn());
        map.put("int",getIntColumn());
        map.put("integer",getIntegerColumn());
        map.put("bigint",getBigintColumn());
        map.put("float",getFloatColumn());
        map.put("double",getDoubleColumn());
        map.put("decimal",getDecimalColumn());
        map.put("bit",getBitColumn());
        map.put("char",getCharColumn());
        map.put("varchar",getVarcharColumn());
        map.put("date",getDateColumn());
        map.put("datetime",getDatetimeColumn());
        map.put("timestamp",getTimestampColumn());
        return map;
    }

    public String getValue(String columnType) {
        return generateReflectColumns().get(columnType);
    }
}
