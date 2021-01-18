package com.yi.easycode.commons.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.yi.easycode.modules.auth.entity.MenuEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yi.easycode.commons.constant.SystemConstant.ROOT_PARENTID;

/**
 * @author yizhicheng
 * @ClassName MenuUtil
 * @Description List转树形菜单
 * @Date 2021/1/5 9:34 上午
 **/
public class MenuUtil {
    
    public static List<Tree<String>> getTreeMenus(List<MenuEntity> menuEntities) {
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        menuEntities.stream().forEach(x ->{
            TreeNode treeNode = new TreeNode();
            treeNode.setId(String.valueOf(x.getId()));
            treeNode.setParentId(String.valueOf(x.getMenuParentId()));
            treeNode.setName(x.getMenuName());
            treeNode.setWeight(x.getMenuSort());
            Map<String, Object> columns = new HashMap<>(16);
            columns.put("menuName",x.getMenuName());
            columns.put("menuLevel",x.getMenuLevel());
            columns.put("menuUrl",x.getMenuUrl());
            columns.put("menuIcon",x.getMenuIcon());
            columns.put("menuDescribe",x.getMenuDescribe());
            columns.put("menuParentId",String.valueOf(x.getMenuParentId()));
            columns.put("menuSort",x.getMenuSort());
            treeNode.setExtra(columns);
            nodeList.add(treeNode);
        });
       return TreeUtil.build(nodeList, ROOT_PARENTID);
    }
}
