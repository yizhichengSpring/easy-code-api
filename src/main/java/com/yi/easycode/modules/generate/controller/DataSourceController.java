package com.yi.easycode.modules.generate.controller;

import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.DatabaseDTO;
import com.yi.easycode.modules.generate.entity.mongodb.DBInfoMongo;
import com.yi.easycode.modules.generate.service.DataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping("list")
    @ApiOperation("数据源列表")
    public Result getAllConnectionList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageResult<DBInfoMongo> pageResult = dataSourceService.getAllConnectionList(pageNum, pageSize);
        return Result.success(pageResult);
    }

    @PostMapping("getAllSchemas")
    @ApiOperation("获取所有schmeas")
    public Result getAllSchemas(@Valid @RequestBody DatabaseDTO dto) {
        List<SelectVO> selectVOS = dataSourceService.getAllSchemas(dto);
        return Result.success(selectVOS);
    }

    @GetMapping("type")
    @ApiOperation("数据源类型")
    public Result getDataSourceType() {
       List<SelectVO> selectList = dataSourceService.getDataSourceType();
        return Result.success(selectList);
    }

    @ApiOperation("测试数据库连接信息")
    @PostMapping("/test")
    public Result testConnection(@Valid @RequestBody DatabaseDTO dto){
        return dataSourceService.testConnection(dto);
    }

    @ApiOperation("保存数据库连接信息")
    @PostMapping("/save")
    public Result saveConnection(@Valid @RequestBody DatabaseDTO dto) {
        return dataSourceService.saveConnection(dto);
    }

    @ApiOperation(("获取表属性"))
    @PostMapping("/getTableColumn")
    public Result getTableColumn(@RequestParam String tableName) {
        log.info("tableName:{}",tableName);
        return dataSourceService.getTableColumn(tableName);
    }


}
