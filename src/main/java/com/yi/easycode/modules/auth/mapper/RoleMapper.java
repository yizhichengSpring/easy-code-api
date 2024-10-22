package com.yi.easycode.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.RoleEntity;
import com.yi.easycode.modules.auth.vo.SelectVO;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 获取角色码值
     * @return
     */
    List<SelectVO> getRoleCode();

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    List<RoleEntity> getRoleByUserId(Long userId);
}
