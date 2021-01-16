package com.yi.easycode.modules.auth.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.MenuUtil;
import com.yi.easycode.commons.util.SelectVOUtil;
import com.yi.easycode.modules.auth.dto.MenuDTO;
import com.yi.easycode.modules.auth.entity.MenuEntity;
import com.yi.easycode.modules.auth.mapper.MenuMapper;
import com.yi.easycode.modules.auth.service.MenuService;
import com.yi.easycode.modules.auth.vo.SelectVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Override
    public PageInfo<MenuEntity> getMenuList(String menuName,Integer pageNum,Integer pageSize) {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        if (StrUtil.isNotBlank(menuName)) {
            wrapper.like("menu_name",menuName);
        }
        wrapper.orderByDesc("create_time");
        PageHelper.startPage(pageNum,pageSize);
        List<MenuEntity> menuEntities = baseMapper.selectList(wrapper);
        return new PageInfo<>(menuEntities);
    }

    @Override
    public List<MenuEntity> getTreeMenuList() {
        List<MenuEntity> menuEntities = baseMapper.selectList(null);
        return menuEntities;
    }

    @Override
    public Result saveMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = new MenuEntity();
        try {
            BeanUtils.copyProperties(menuDTO,menuEntity);
            baseMapper.insert(menuEntity);
        }catch (Exception e) {
            log.error("error menu save",e);
            throw new ApiException("保存菜单失败");
        }
        return Result.success();
    }

    @Override
    public Result updateMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = new MenuEntity();
        try {
            BeanUtils.copyProperties(menuDTO,menuEntity);
            baseMapper.updateById(menuEntity);
        }catch (Exception e) {
            throw new ApiException("修改菜单失败");
        }
        return Result.success();
    }

    @Override
    public Result deleteMenu(Long id) {
        MenuEntity menuEntity = baseMapper.selectById(id);
        menuEntity.setDelFlag(DeleteEnums.DEL.getCode());
        baseMapper.updateById(menuEntity);
        return Result.success();
    }

    @Override
    public Result treeMenu() {
        List<MenuEntity> menuEntities = baseMapper.getAllMenu();
        List<Tree<String>> treeList = MenuUtil.getTreeMenus(menuEntities);
        return Result.success(treeList);
    }

    @Override
    public Result getMenuCode() {
        List<SelectVO> selectList = SelectVOUtil.initSelectVOList();
        selectList.addAll(baseMapper.getMenuCodes());
        return Result.success(selectList);
    }

}
