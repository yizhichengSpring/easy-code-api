package com.yi.easycode.modules.user.controller;

import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.result.PageResult;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.user.dto.BindRoleMenuDTO;
import com.yi.easycode.modules.user.dto.RoleDTO;
import com.yi.easycode.modules.user.entity.RoleEntity;
import com.yi.easycode.modules.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yizhicheng
 * @ClassName RoleController
 * @Description 角色
 * @Date 2020/12/30 2:52 下午
 **/
@RestController
@RequestMapping("/role")
@Api(value = "角色模块",tags = "用户模块")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("角色列表")
    @PostMapping(value = "list")
    public Result list(@RequestParam(value = "roleName",required = false) String userName,
                        @RequestParam("pageNum") Integer pageNum,
                        @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RoleEntity> pageInfo = roleService.getRoleList(userName,pageNum,pageSize);
        return Result.success(PageResult.convert(pageInfo));
    }

    @ApiOperation("新增角色")
    @PostMapping("/saveRole")
    public Result saveRole(@RequestBody RoleDTO roleDTO) {
        return roleService.saveRoleEntity(roleDTO);
    }

    @ApiOperation("修改角色")
    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody RoleDTO roleDTO) {
        return roleService.updateRoleEntity(roleDTO);
    }

    @ApiOperation("删除角色")
    @GetMapping("/deleteRole/{id}")
    public Result deleteRole(@PathVariable("id") Long id) {
        return roleService.deleteRoleEntity(id);
    }
    
    @ApiOperation("角色码值")
    @PostMapping("/getRoleCode")
    public Result getRoleCode() {
        return roleService.getRoleCode();
    }
    
    @ApiOperation("角色绑定菜单信息")
    @PostMapping("/bindRoleMenus")
    public Result bindRoleMenus(@RequestBody @Valid BindRoleMenuDTO dto) {
        return roleService.bindRoleMenus(dto);
    }
}
