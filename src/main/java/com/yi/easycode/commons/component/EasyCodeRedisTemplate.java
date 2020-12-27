package com.yi.easycode.commons.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yizhicheng
 * @ClassName RedisTemplate
 * @Description Redis 工具类
 * @Date 2020/9/7 3:13 PM
 **/
@Component
@Slf4j
public class EasyCodeRedisTemplate {

    /**
     *  TODO: 这里只封装了现有可能需要的字符串相关方法，后续如果需要别的类型，继续修改此类即可。
     *  redisTemplate.opsForValue();　　操作字符串
     *  redisTemplate.opsForHash();　　 操作hash
     *  redisTemplate.opsForList();　　 操作list
     *  redisTemplate.opsForSet();　　  操作set
     *  redisTemplate.opsForZSet();　 　操作有序set
     *  ******************************************************************
     *  注:redis中key的过期时间，请统一(秒)为单位
     *  redis中key为了可读性和可管理型，请保持统一
     *  以业务名(或数据库名)为前缀(防止key冲突)，用冒号分隔
     *  例: easycode:user:token
     *  例: easycode:order:shop
     */

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * set key value
     * @param key
     * @param value
     */
    public void set(String key,String value) {
        log.info("set redis info key:{},value:{}",key,value);
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * set key value 并设置过期时间，单位为秒
     * @param key
     * @param value
     * @param seconds
     */
    public void set(String key,String value,Long seconds) {
        log.info("set redis info key:{},value:{},seconds:{}",key,value,seconds);
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    /**
     * get key 通过key 取值
     * @param key
     * @return
     */
    public String get(String key) {
        log.info("get redis info key:{}",key);
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key 获取对应 过期时间
     * expire key
     */
    public Long getExpire(String key) {
        log.info("get expireTime info key:{}",key);
        return redisTemplate.getExpire(key);
    }

    /**
     * 设置过期时间
     */
    public void setExpire(String key,Long seconds) {
        log.info("set expireTime info key:{},seconds:{}",key,seconds);
        redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
    }

    /**
     * 根据key 删除
     */
    public void del(String key) {
        log.info("del key:{}",key);
        redisTemplate.delete(key);
    }

    /**
     *  检查key 是否存在
     */
    public boolean exists(String key) {
        log.info("exists key:{}",key);
        return redisTemplate.hasKey(key);
    }

}
