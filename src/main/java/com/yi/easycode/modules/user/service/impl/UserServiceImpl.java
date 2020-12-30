package com.yi.easycode.modules.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.component.EasyCodeRedisTemplate;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.JwtUtil;
import com.yi.easycode.modules.user.dto.BindUserRoleDTO;
import com.yi.easycode.modules.user.dto.UserDTO;
import com.yi.easycode.modules.user.entity.UserEntity;
import com.yi.easycode.modules.user.entity.UserRoleBindEntity;
import com.yi.easycode.modules.user.mapper.UserMapper;
import com.yi.easycode.modules.user.mapper.UserRoleBindMapper;
import com.yi.easycode.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yi.easycode.modules.constant.RedisConstant.TOKEN_REDIS_KEY;

/**
 * @author yizhicheng
 * @ClassName UserServiceImpl
 * @Description 用户模块
 * @Date 2020/12/24 22:18 下午
 **/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EasyCodeRedisTemplate redisTemplate;
    @Value("${jwt.expireTime}")
    private Long expireTime;
    @Value("${login.salt}")
    private String salt;
    @Autowired
    private UserRoleBindMapper bindMapper;

    @Override
    public UserEntity register(UserDTO userDTO) {
        checkUser(userDTO);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO,user);
        String pwd = user.getPassword();
        String newPassword = pwd+salt;
        user.setPassword(SecureUtil.md5(newPassword));
        baseMapper.insert(user);
        return user;
    }

    @Override
    public String login(UserDTO userDTO) {
        String userName = userDTO.getUserName();
        QueryWrapper<UserEntity> userwrapper = new QueryWrapper<>();
        userwrapper.eq("user_name",userName);
        UserEntity tmpUserEntity = baseMapper.selectOne(userwrapper);
        if (null == tmpUserEntity) {
            throw new ApiException("用户登录失败,该用户不存在，请注册");
        }
        String md5Password = SecureUtil.md5(userDTO.getPassword()+salt);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName).
                eq("password",md5Password);
        UserEntity userEntity = baseMapper.selectOne(wrapper);
        if (null == userEntity) {
            throw new ApiException("用户登录失败,请检查密码是否正确");
        }
        log.info("用户信息为,{}",userEntity);
        String token = jwtUtil.generateJwtToken(userEntity.getUserName());
        log.info("生成的token信息为,{}",userEntity);
        redisTemplate.set(TOKEN_REDIS_KEY + userEntity.getUserId(),token,expireTime);
        return token;
    }


    @Override
    public Boolean loginOut(String token) {
        String userName = jwtUtil.getUserName(token);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        UserEntity userEntity = baseMapper.selectOne(wrapper);
        if (null == userEntity) {
            throw new ApiException("非法token");
        }
        redisTemplate.del(TOKEN_REDIS_KEY+userEntity.getUserId());
        return true;
    }

    /**
     * 校验用户是否存在
     * @param userDTO
     * @return
     */
    private boolean checkUser(UserDTO userDTO) {
        log.info("userDTO info {}",userDTO);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userDTO.getUserName()).
                eq("mobile",userDTO.getMobile());
        List<UserEntity> users = baseMapper.selectList(wrapper);
        if (CollectionUtil.isNotEmpty(users)) {
            throw new ApiException("此用户已存在，请修改用户名或密码");
        }
        return true;
    }

    @Override
    public PageInfo<UserEntity> getUserList(String userName, Integer pageNum, Integer pageSize) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        if (StrUtil.isNotBlank(userName)) {
            wrapper.eq("user_name",userName);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> userEntities = baseMapper.selectList(wrapper);
        return new PageInfo<>(userEntities);
    }

    @Transactional
    @Override
    public Result bindUserRoles(BindUserRoleDTO dto) {
        Long userId = dto.getUserId();
        UserEntity entity = baseMapper.selectById(userId);
        if (null == entity) {
            throw new ApiException("用户不存在");
        }
        //删除之前该用户的所有角色
        bindMapper.deleteByUserId(userId);
        //保存新角色权限
        for (Long roleId : dto.getRoleIds()) {
            UserRoleBindEntity bindEntity = new UserRoleBindEntity();
            bindEntity.setUserId(userId);
            bindEntity.setRoleId(roleId);
            bindMapper.insert(bindEntity);
        }
        return Result.success();
    }
}
