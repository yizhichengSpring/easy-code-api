package com.yi.easycode.modules.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.user.dto.BindRoleMenuDTO;
import com.yi.easycode.modules.user.dto.RoleDTO;
import com.yi.easycode.modules.user.entity.RoleEntity;
import com.yi.easycode.modules.user.entity.RoleMenuBindEntity;
import com.yi.easycode.modules.user.mapper.RoleMapper;
import com.yi.easycode.modules.user.mapper.RoleMenuBindMapper;
import com.yi.easycode.modules.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private RoleMenuBindMapper bindMapper;

    @Override
    public PageInfo<RoleEntity> getRoleList(String username, Integer pageNum, Integer pageSize) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        if (StrUtil.isNotBlank(username)) {
            wrapper.like("role_name",username);
        }
        wrapper.orderByDesc("create_time");
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
        entity.setDelFlag(DeleteEnums.DEL.getCode().intValue());
        baseMapper.updateById(entity);
        return Result.success();
    }

    @Override
    public Result getRoleCode() {
        return Result.success(baseMapper.getRoleCode());
    }

    @Transactional
    @Override
    public Result bindRoleMenus(BindRoleMenuDTO dto) {
        Long roleId = dto.getRoleId();
        RoleEntity entity = baseMapper.selectById(roleId);
        if (null == entity) {
            throw new ApiException("角色不存在");
        }
        //删除之前该角色的所有菜单
        bindMapper.deleteByRoleId(roleId);
        //保存新角色-菜单权限
        for (Long menuId : dto.getMenuIds()) {
            RoleMenuBindEntity bindEntity = new RoleMenuBindEntity();
            bindEntity.setRoleId(roleId);
            bindEntity.setMenuId(menuId);
            bindMapper.insert(bindEntity);
        }
        return Result.success();
    }
}
