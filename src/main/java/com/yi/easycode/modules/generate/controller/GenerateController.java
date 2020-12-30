package com.yi.easycode.modules.generate.controller;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.generate.dto.GenerateDTO;
import com.yi.easycode.modules.generate.service.GenerateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/code")
    public Result generateCode(GenerateDTO generateDTO) {
        return generateService.generateCode(generateDTO);
    }


}
