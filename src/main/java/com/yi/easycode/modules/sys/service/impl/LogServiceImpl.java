package com.yi.easycode.modules.sys.service.impl;

import com.yi.easycode.commons.component.EasyCodeMongoTemplate;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.util.PageListUtil;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import com.yi.easycode.modules.sys.entity.ExceptionLogMongo;
import com.yi.easycode.modules.sys.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yizhicheng
 * @ClassName LoginLogServiceImpl
 * @Description 登录日志service实现类
 * @Date 2021/1/10 1:07 上午
 **/
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private EasyCodeMongoTemplate mongoTemplate;

    @Override
    public PageResult<LoginLogMongo> findLoginLogAll(Integer pageNum, Integer pageSize) {
        //todo 这里直接查询了全部，然后在Java中分页，这样做数据多了之后，影响效率，后期需要修改
        List<LoginLogMongo> loginList = mongoTemplate.findAll("loginTime",LoginLogMongo.class);
        PageResult<LoginLogMongo> pageResult = PageListUtil.startPage(pageNum,pageSize,loginList);
        return pageResult;
    }

    @Override
    public PageResult<ExceptionLogMongo> findExceptionLogAll(Integer pageNum, Integer pageSize) {
        List<ExceptionLogMongo> loginList = mongoTemplate.findAll("exceptionTime", ExceptionLogMongo.class);
        PageResult<ExceptionLogMongo> pageResult = PageListUtil.startPage(pageNum,pageSize,loginList);
        return pageResult;
    }
}
