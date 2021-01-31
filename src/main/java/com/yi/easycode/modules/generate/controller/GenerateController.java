package com.yi.easycode.modules.generate.controller;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.BaseDTO;
import com.yi.easycode.modules.generate.dto.GenerateDTO;
import com.yi.easycode.modules.generate.service.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName GenerateController
 * @Description 代码生成类
 * @Date 2020/12/20 1:52 下午
 **/
@RestController
@RequestMapping("/generate")
@Api(value = "生成代码",tags = "生成代码")
@Slf4j
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @PostMapping("/list")
    @ApiOperation("生成记录")
    public Result generateList(@RequestBody BaseDTO dto) {
        return generateService.generateList(dto.getPageNum(), dto.getPageSize());
    }

    @PostMapping(value = "/code",produces = {"application/octet-stream"})
    @ApiOperation("生成代码")
    public Result generateCode(@RequestBody GenerateDTO generateDTO, HttpServletResponse response) {
        return generateService.generateCode(generateDTO,response);
    }

    @GetMapping("/datasourceList")
    @ApiOperation("获取数据源列表")
    public Result datasourceList() {
        return generateService.datasourceList();
    }

    @GetMapping("getSchemaByDataSource/{id}")
    @ApiOperation("获取所有schmeas")
    public Result getSchemaByDataSource(@PathVariable Long id) {
        List<SelectVO> selectList = generateService.getSchemaByDataSource(id);
        return Result.success(selectList);
    }

}
