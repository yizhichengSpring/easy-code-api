package com.yi.easycode.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.auth.entity.MenuEntity;
import com.yi.easycode.modules.auth.vo.SelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {
    /**
     * 获取菜单列表
     * @param menuName
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<MenuEntity> getMenuList(
            @Param("menuName") String menuName,
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize);

    /**
     * 获取所有菜单
     * @return
     */
    List<MenuEntity> getAllMenu();

    /**
     * 获取菜单码值
     * @return
     */
    List<SelectVO> getMenuCodes();

    /**
     * 根据多个角色id，查询对应的菜单
     * @param roleIds
     * @return
     */
    List<MenuEntity> getMenusByRoleIds(@Param("roleIds") List<Long> roleIds);


    /**
     * 根据单个角色id，查询对应的菜单
     * @param roleId
     * @return
     */
    List<MenuEntity> getMenusByRoleId(@Param("roleId") Long roleId);
}
