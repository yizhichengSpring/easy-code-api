package com.yi.easycode;

import cn.hutool.core.date.DateUtil;
import com.yi.easycode.modules.generate.entity.mongodb.GenerateLogMongo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yizhicheng
 * @ClassName GenerateTest
 * @Description 生成管理测试
 * @Date 2021/1/31 3:14 下午
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class GenerateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void save() {
        GenerateLogMongo logMongo = new GenerateLogMongo();
        logMongo.setDataSourceName("数据源二");
        logMongo.setPackageName("com.easycode");
        logMongo.setAuther("yizhicheng");
        logMongo.setDescription("测试类");
        logMongo.setOpenLombok(true);
        logMongo.setOpenSwagger(true);
        logMongo.setOpenSerializable(true);
        logMongo.setTableName("t_menu");
        logMongo.setGenerateTime(DateUtil.now());
        mongoTemplate.save(logMongo);


    }
}
