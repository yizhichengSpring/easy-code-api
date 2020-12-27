package com.yi.easycode.modules.controller;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.dto.DatabaseDTO;
import com.yi.easycode.modules.service.ConnectionService;
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
@RequestMapping("/conn")
@Api(value = "数据库连接模块",tags = "数据库连接模块")
@Slf4j
public class ConnectionController {
    @Autowired
    private ConnectionService connectionService;

    /**
     * 校验数据库连接信息，如果成功，返回该数据库下所有表信息
     * @param dto
     * @return
     */
    @ApiOperation("校验数据库连接信息")
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
