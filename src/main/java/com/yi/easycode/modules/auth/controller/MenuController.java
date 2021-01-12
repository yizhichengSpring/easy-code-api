package com.yi.easycode.modules.auth.controller;

import cn.hutool.core.lang.tree.Tree;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.MenuUtil;
import com.yi.easycode.modules.auth.dto.MenuDTO;
import com.yi.easycode.modules.auth.entity.MenuEntity;
import com.yi.easycode.modules.auth.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yizhicheng
 * @ClassName MenuController
 * @Description 菜单管理
 * @Date 2020/12/31 9:17 上午
 **/
@RestController
@RequestMapping("/menu")
@Api(value = "菜单模块",tags = "菜单模块")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("菜单列表")
    @PostMapping("/list")
    public Result getMenuList(@RequestParam(value = "menuName",required = false) String menuName,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MenuEntity> pageInfo = menuService.getMenuList(menuName,pageNum,pageSize);
        return Result.success(PageResult.convert(pageInfo));
    }

    @ApiOperation("获取树形菜单列表")
    @GetMapping("/treeList")
    public Result treeList() {
        List<MenuEntity> menuList = menuService.getTreeMenuList();
        List<Tree<String>> treeList = MenuUtil.getTreeMenus(menuList);
        return Result.success(treeList);
    }

    @ApiOperation("新增菜单")
    @PostMapping("/save")
    public Result saveMenu(@RequestBody @Valid MenuDTO menuDTO) {
        return menuService.saveMenu(menuDTO);
    }

    @ApiOperation("修改菜单")
    @PostMapping("/update")
    public Result updateMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.updateMenu(menuDTO);
    }

    @ApiOperation("删除菜单")
    @GetMapping("/delete/{id}")
    public Result deleteMenu(@PathVariable Long id) {
        return menuService.deleteMenu(id);
    }

    @ApiOperation("获取树形菜单")
    @GetMapping("/treeMenu")
    public Result treeMenu() {
        return menuService.treeMenu();

    }

    @ApiOperation("菜单码值")
    @GetMapping("/getMenuCode")
    public Result getMenuCode() {
        return menuService.getMenuCode();
    }
}
