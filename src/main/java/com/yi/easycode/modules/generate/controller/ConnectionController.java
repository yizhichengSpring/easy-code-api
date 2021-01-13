package com.yi.easycode.modules.generate.controller;

import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.entity.mongodb.LoginLogMongo;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.entity.mongodb.DBInfoMongo;
import com.yi.easycode.modules.generate.service.ConnectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yizhicheng
 * @ClassName ConnectionController
 * @Description 获取数据库相应连接
 * @Date 2020/12/19 7:28 下午
 **/
@RestController
@RequestMapping("/datasource")
@Api(value = "数据库连接模块",tags = "数据库连接模块")
@Slf4j
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping("list")
    @ApiOperation("数据源列表")
    public Result getAllConnectionList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageResult<DBInfoMongo> pageResult = connectionService.getAllConnectionList(pageNum, pageSize);
        return Result.success(pageResult);
    }

    @ApiOperation("测试数据库连接信息")
    @PostMapping("/test")
    public Result testConnection(@Valid @RequestBody DatabaseDTO dto){
        return connectionService.testConnection(dto);
    }

    @ApiOperation("保存数据库连接信息")
    @PostMapping("/saveConnection")
    public Result saveConnection(@Valid @RequestBody DatabaseDTO dto) {
        return connectionService.saveConnection(dto);
    }

    @ApiOperation(("获取表属性"))
    @PostMapping("/getTableColumn")
    public Result getTableColumn(@RequestParam String tableName) {
        log.info("tableName:{}",tableName);
        return connectionService.getTableColumn(tableName);
    }


}
