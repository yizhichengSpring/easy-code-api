package com.yi.easycode.modules.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.dto.BindRoleMenuDTO;
import com.yi.easycode.modules.auth.dto.RoleDTO;
import com.yi.easycode.modules.auth.entity.MenuEntity;
import com.yi.easycode.modules.auth.entity.RoleEntity;
import com.yi.easycode.modules.auth.entity.RoleMenuBindEntity;
import com.yi.easycode.modules.auth.mapper.MenuMapper;
import com.yi.easycode.modules.auth.mapper.RoleMapper;
import com.yi.easycode.modules.auth.mapper.RoleMenuBindMapper;
import com.yi.easycode.modules.auth.service.RoleService;
import com.yi.easycode.modules.auth.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo<RoleVO> getRoleList(String username, Integer pageNum, Integer pageSize) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        if (StrUtil.isNotBlank(username)) {
            wrapper.like("role_name",username);
        }
        wrapper.orderByDesc("create_time");
        PageHelper.startPage(pageNum,pageSize);
        List<RoleEntity> roleEntities = baseMapper.selectList(wrapper);
        List<RoleVO> roleVOList = new ArrayList();
        if (CollectionUtil.isNotEmpty(roleEntities)) {
            roleEntities.stream().forEach(x -> {
                Long roleId = x.getId();
                //获取菜单信息
                List<MenuEntity> menuEntities = menuMapper.getMenusByRoleId(roleId);
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(x,roleVO);
                roleVO.setMenus(menuEntities);
                roleVOList.add(roleVO);
            });
        }
        return new PageInfo<>(roleVOList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveRoleEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", roleDTO.getRoleName());
        //保存角色
        RoleEntity tmpRole = baseMapper.selectOne(wrapper);
        if (null == tmpRole) {
            BeanUtils.copyProperties(roleDTO, roleEntity);
            baseMapper.insert(roleEntity);
            //保存角色-菜单管理表
            bindRoleAndMenus(roleEntity.getId(), roleDTO.getMenuIds());
            return Result.success();
        }
        return Result.fail("该角色已存在");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateRoleEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, roleEntity);
        baseMapper.updateById(roleEntity);
        //保存角色-菜单管理表
        bindRoleAndMenus(roleEntity.getId(), roleDTO.getMenuIds());
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result bindRoleMenus(BindRoleMenuDTO dto) {
        Long roleId = dto.getRoleId();
        RoleEntity entity = baseMapper.selectById(roleId);
        if (null == entity) {
            throw new ApiException("角色不存在");
        }
        /**
         * step1: 删除之前该角色的所有菜单
         * step2: 保存新角色-菜单权限
         */
        bindRoleAndMenus(roleId,dto.getMenuIds());
        return Result.success();
    }
    
    private void bindRoleAndMenus(Long roleId,List<Long> menuIds) {
        if (CollectionUtil.isNotEmpty(menuIds)) {
            //删除之前该角色的所有菜单
            bindMapper.deleteByRoleId(roleId);
            //保存新角色-菜单权限
            for (Long menuId : menuIds) {
                RoleMenuBindEntity bindEntity = new RoleMenuBindEntity();
                bindEntity.setRoleId(roleId);
                bindEntity.setMenuId(menuId);
                bindMapper.insert(bindEntity);
            }
        }
    }
}
