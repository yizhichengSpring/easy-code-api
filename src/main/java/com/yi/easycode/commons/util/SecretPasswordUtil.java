package com.yi.easycode.commons.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author yizhicheng
 * @ClassName SecureUtil
 * @Description 对称加密工具类
 * @Date 2021/1/16 5:37 下午
 **/
public class SecretPasswordUtil {

    /**
     * 16进制加密
     * @param secret
     * @param password
     * @return
     */
    public static String encryptHex(String secret,String password) {
        AES aes1 = SecureUtil.aes(secret.getBytes());
        return aes1.encryptHex(password);
    }

    /**
     * 十六进制解密
     * @param secret
     * @param secretPassword
     * @return
     */
    public static String decryptStr(String secret,String secretPassword) {
        AES aes = SecureUtil.aes(secret.getBytes());
        return aes.decryptStr(secretPassword);
    }
}
