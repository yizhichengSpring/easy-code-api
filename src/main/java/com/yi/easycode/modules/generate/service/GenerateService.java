package com.yi.easycode.modules.generate.service;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.dto.GenerateDTO;

import java.util.List;

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

    /**
     * 获取数据源列表
     * @return
     */
    Result datasourceList();

    /**
     * 获取相应数据源下的schema
     * @param id
     * @return
     */
    List<SelectVO> getSchemaByDataSource(Long id);
}
