package com.yi.easycode.modules.user.controller;

import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.user.dto.MenuDTO;
import com.yi.easycode.modules.user.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getMenuList() {
       return menuService.getMenuList();
    }

    @ApiOperation("新增菜单")
    @PostMapping("/saveMenu")
    public Result saveMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.saveMenu(menuDTO);
    }

    @ApiOperation("修改菜单")
    @PostMapping("/updateMenu")
    public Result updateMenu(MenuDTO menuDTO) {
        return menuService.updateMenu(menuDTO);
    }

    @ApiOperation("删除菜单")
    @PostMapping("/deleteMenu/{id}")
    public Result deleteMenu(@PathVariable Long id) {
        return menuService.deleteMenu(id);
    }

    @ApiOperation("获取树形菜单")
    @PostMapping("/treeMenu")
    public Result treeMenu() {
        return menuService.treeMenu();

    }

    @ApiOperation("菜单码值")
    @PostMapping("/getMenuCode")
    public Result getMenuCode() {
        return menuService.getMenuCode();
    }
}