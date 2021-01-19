package com.yi.easycode;

import com.yi.easycode.modules.sys.mapper.WhiteUrlMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yizhicheng
 * @ClassName DBInfoTest
 * @Description 测试存放dbinfo
 * @Date 2020/01/19 8:26 下午
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WhiteUrlTest {

    @Autowired
    private WhiteUrlMapper whiteUrlMapper;


    @Test
    public void whiteUrlList() {
       whiteUrlMapper.selectList(null).forEach(x -> log.info(x.toString()));

    }

}
