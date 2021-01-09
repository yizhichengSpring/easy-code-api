package com.yi.easycode.commons.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author yizhicheng
 * @ClassName EasyCodeMongoTemplate
 * @Description MongoDB 模板类
 * @Date 2020/12/27 9:03 下午
 **/
@Component
@Slf4j
public class EasyCodeMongoTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * @param obj
     * @param <T>
     * @return
     */
    public  <T> T save(T obj) {
        log.info("save mongodb ,{}",obj);
        return mongoTemplate.save(obj);
    }

    /**
     * 查询全部
     * @param entityClass
     * @param <T>
     * @return
     */
    public  <T> T findAll(Class entityClass) {
        log.info("默认查询全部对象,{}",entityClass.getName());
        return (T)mongoTemplate.findAll(entityClass);
    }

    /**
     * 查询多个
     * @param param
     * @param paramVal
     * @param entityClass
     * @param <T>
     * @return
     */
    public  <T> T find(String param,Object paramVal,Class entityClass) {
        log.info("find mongodb,param:{},paramVal:{}",param,paramVal);
        Query query = new Query();
        query.addCriteria(Criteria.where(param).is(paramVal));
        return (T) mongoTemplate.find(query,entityClass);
    }

    /**
     * 查询单个
     * @param param
     * @param paramVal
     * @param entityClass
     * @param <T>
     * @return
     */
    public  <T> T findOne(String param,Object paramVal,Class entityClass) {
        log.info("findOne mongodb,param:{},paramVal:{}",param,paramVal);
        Query query = new Query();
        query.addCriteria(Criteria.where(param).is(paramVal));
        return (T) mongoTemplate.findOne(query,entityClass);
    }



}
