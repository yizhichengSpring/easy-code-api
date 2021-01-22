package com.yi.easycode.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.sys.dto.WhiteUrlDTO;
import com.yi.easycode.modules.sys.entity.WhiteUrlEntity;
import com.yi.easycode.modules.sys.service.WhiteUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yizhicheng
 * @ClassName WhiteUrlController
 * @Description 白名单模块
 * @Date 2020/12/20 21:20 下午
 **/
@RestController
@RequestMapping("/whiteUrl")
@Api(value = "白名单模块",tags = "白名单模块")
@Slf4j
public class WhiteUrlController {

    @Autowired
    private WhiteUrlService whiteUrlService;
    
    @ApiOperation("获取所有记录")
    @PostMapping("/list")
    public Result getWhiteUrlList(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        PageInfo<WhiteUrlEntity> pageInfo = whiteUrlService.getWhiteUrlList(pageNum,pageSize);
        return Result.success(PageResult.convert(pageInfo));
    }
    
    @ApiOperation("获取请求方法类型")
    @PostMapping("/requestMethod")
    public Result getRequestMethod() { 
        return whiteUrlService.getRequestMethod();
    }

    @ApiOperation("添加")
    @PostMapping(value = "add")
    public Result addWhiteUrl(@RequestBody @Valid WhiteUrlDTO dto) {
        return whiteUrlService.addWhiteUrl(dto);
    }

    @ApiOperation("修改")
    @PostMapping(value = "update")
    public Result updateWhiteUrl(@RequestBody @Valid WhiteUrlDTO dto) {
        return whiteUrlService.updateWhiteUrl(dto);
    }

    @ApiOperation("删除")
    @GetMapping(value = "delete/{id}")
    public Result deleteWhiteUrl(@PathVariable Long id) {
        return whiteUrlService.deleteWhiteUrl(id);
    }

}

