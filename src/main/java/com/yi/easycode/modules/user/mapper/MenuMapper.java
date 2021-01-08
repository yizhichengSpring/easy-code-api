package com.yi.easycode.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.easycode.modules.user.entity.MenuEntity;
import com.yi.easycode.modules.user.vo.SelectVO;
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

    List<MenuEntity> getMenuList(
            @Param("menuName") String menuName,
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize);

    List<MenuEntity> getAllMenu();

    List<SelectVO> getMenuCodes();

    List<MenuEntity> getMenusByRoleId(@Param("roleIds") List<Long> roleIds);
}
