package com.yi.easycode.modules.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.auth.dto.MenuDTO;
import com.yi.easycode.modules.auth.entity.MenuEntity;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 获取菜单列表
     * @return
     */
    PageInfo<MenuEntity> getMenuList(String menuName, Integer pageNum, Integer pageSize);
    /**
     * 保存菜单
     * @return
     */
    Result saveMenu(MenuDTO menuDTO);
    /**
     * 修改菜单
     * @return
     */
    Result updateMenu(MenuDTO menuDTO);
    /**
     * 删除菜单
     * @return
     */
    Result deleteMenu(Long id);
    /**
     * 获取树形菜单
     * @return
     */
    Result treeMenu();
    /**
     * 获取菜单码值
     * @return
     */
    Result getMenuCode();
}
