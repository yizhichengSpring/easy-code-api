package com.yi.easycode;

import com.yi.easycode.commons.util.SecretPasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yizhicheng
 * @ClassName SecretPasswordTest
 * @Description 密码加密、解密测试类
 * @Date 2021/1/16 5:45 下午
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SecretPasswordTest {
    @Value("${easycodeSecret}")
    private String secretKey;

    @Test
    public void encryptAnddecryptStr() {
        String dataSourcePwd = "passw0rd";
        String secretPassword = SecretPasswordUtil.encryptHex(secretKey,dataSourcePwd);
        log.info("encryptHex password,{}",secretPassword);
        String pwd = SecretPasswordUtil.decryptStr(secretKey,secretPassword);
        log.info("pwd,{}",pwd);
    }
}
