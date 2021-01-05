package com.yi.easycode.modules.user.service.impl;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.MenuUtil;
import com.yi.easycode.modules.user.dto.MenuDTO;
import com.yi.easycode.modules.user.entity.MenuEntity;
import com.yi.easycode.modules.user.mapper.MenuMapper;
import com.yi.easycode.modules.user.service.MenuService;
import com.yi.easycode.modules.user.vo.SelectVO;
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
    public Result getMenuList() {
        List<MenuEntity> menuEntities = baseMapper.getMenuList();
        menuEntities.stream().forEach(x -> log.info("菜单列表:{}",x.toString()));
        return Result.success(menuEntities);
    }

    @Override
    public Result saveMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = new MenuEntity();
        try {
            BeanUtils.copyProperties(menuDTO,menuEntity);
            baseMapper.insert(menuEntity);
        }catch (Exception e) {
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
        List<MenuEntity> menuEntities = baseMapper.getMenuList();
        List<Tree<String>> treeList = MenuUtil.getTreeMenus(menuEntities);
        return Result.success(treeList);
    }

    @Override
    public Result getMenuCode() {
        List<SelectVO> selectVOS = baseMapper.getMenuCodes();
        return Result.success(selectVOS);
    }
}
