package com.yi.easycode.modules.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.user.dto.RoleDTO;
import com.yi.easycode.modules.user.entity.RoleEntity;
import com.yi.easycode.modules.user.mapper.RoleMapper;
import com.yi.easycode.modules.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Override
    public PageInfo<RoleEntity> getRoleList(String username, Integer pageNum, Integer pageSize) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        if (StrUtil.isNotBlank(username)) {
            wrapper.eq("user_name",username);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<RoleEntity> roleEntities = baseMapper.selectList(wrapper);
        return new PageInfo<>(roleEntities);
    }

    @Override
    public Result saveRoleEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", roleDTO.getRoleName());
        RoleEntity tmpRole = baseMapper.selectOne(wrapper);
        if (null == tmpRole) {
            BeanUtils.copyProperties(roleDTO, roleEntity);
            baseMapper.insert(roleEntity);
            return Result.success();
        }
        return Result.fail("该角色已存在");
    }

    @Override
    public Result updateRoleEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, roleEntity);
        baseMapper.updateById(roleEntity);
        return Result.success();
    }

    @Override
    public Result deleteRoleEntity(Long id) {
        RoleEntity entity = baseMapper.selectById(id);
        entity.setDelFlag(1);
        baseMapper.updateById(entity);
        return Result.success();
    }
}
