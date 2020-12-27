package com.yi.easycode.modules.service;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.dto.GenerateDTO;
import com.yi.easycode.modules.entity.GenerateEntity;

/**
 * @author yizhicheng
 * @ClassName GenerateService
 * @Description 代码生成Service
 * @Date 2020/12/20 1:54 下午
 **/
public interface GenerateService {
    /**
     * 生成代码
     * @param generateDTO
     * @return
     */
    Result generateCode(GenerateDTO generateDTO);
}
