package com.yi.easycode.modules.generate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.MenuEntity;
import com.yi.easycode.modules.auth.vo.SelectVO;
import com.yi.easycode.modules.generate.entity.DBInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据源 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-01-16
 */
public interface DBInfoMapper extends BaseMapper<DBInfoEntity> {

}
