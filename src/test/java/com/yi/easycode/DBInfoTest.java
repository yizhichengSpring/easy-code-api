package com.yi.easycode;

import com.yi.easycode.modules.entity.mongodb.DBInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yizhicheng
 * @ClassName DBInfoTest
 * @Description 测试存放dbinfo
 * @Date 2020/12/27 8:26 下午
 **/
@SpringBootTest
@RunWith(SpringRunner.class)

public class DBInfoTest {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void save() {
        DBInfo dbInfo = new DBInfo();
        dbInfo.setUserId(4L);
        dbInfo.setConnectionName("连接3");
        dbInfo.setType("MYSQL");
        dbInfo.setUrl("127.0.0.1");
        dbInfo.setPort("3306");
        dbInfo.setName("root");
        dbInfo.setPassword("passw0rd");
        dbInfo.setDatabaseName("easy-code");
        mongoTemplate.save(dbInfo);

    }

}
