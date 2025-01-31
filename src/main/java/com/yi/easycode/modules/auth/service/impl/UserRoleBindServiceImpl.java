package com.yi.easycode.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.easycode.modules.auth.entity.UserRoleBindEntity;
import com.yi.easycode.modules.auth.mapper.UserRoleBindMapper;
import com.yi.easycode.modules.auth.service.UserRoleBindService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色绑定表 服务实现类
 * </p>
 *
 * @author yizhicheng
 * @since 2020-12-30
 */
@Service
public class UserRoleBindServiceImpl extends ServiceImpl<UserRoleBindMapper, UserRoleBindEntity> implements UserRoleBindService {

}
