package com.yi.easycode.modules.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.annotation.LoginLog;
import com.yi.easycode.commons.component.EasyCodeRedisTemplate;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.FailInfo;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.commons.util.JwtUtil;
import com.yi.easycode.modules.auth.dto.BindUserRoleDTO;
import com.yi.easycode.modules.auth.dto.LoginDTO;
import com.yi.easycode.modules.auth.dto.UserDTO;
import com.yi.easycode.modules.auth.entity.MenuEntity;
import com.yi.easycode.modules.auth.entity.RoleEntity;
import com.yi.easycode.modules.auth.entity.UserEntity;
import com.yi.easycode.modules.auth.entity.UserRoleBindEntity;
import com.yi.easycode.modules.auth.mapper.MenuMapper;
import com.yi.easycode.modules.auth.mapper.RoleMapper;
import com.yi.easycode.modules.auth.mapper.UserMapper;
import com.yi.easycode.modules.auth.mapper.UserRoleBindMapper;
import com.yi.easycode.modules.auth.service.UserService;
import com.yi.easycode.modules.auth.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Value("${jwt.headerKeyPrefix}")
    private String bearer;
    @Value("${jwt.headerValuePrefix}")
    private String headerValuePrefix;
    @Autowired
    private UserRoleBindMapper bindMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserVO register(UserDTO userDTO) {
        checkUser(userDTO);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO,user);
        String pwd = user.getPassword();
        String newPassword = pwd+salt;
        user.setPassword(SecureUtil.md5(newPassword));
        baseMapper.insert(user);
        //绑定用户和角色之间的关系
        bindUserAndRoleId(user.getUserId(),userDTO.getRoleIds());
        log.info("userId:{}",user.getUserId());
        return baseMapper.getUserInfo(user.getUserId());
    }

    @LoginLog
    @Override
    public Result login(LoginDTO loginDTO) {
        QueryWrapper<UserEntity> userwrapper = new QueryWrapper<>();
        userwrapper.eq("user_name",loginDTO.getUserName());
        UserEntity tmpUserEntity = baseMapper.selectOne(userwrapper);
        if (null == tmpUserEntity) {
            return Result.fail(FailInfo.FAIL_USER_NOTFOUND);
        }
        String md5Password = SecureUtil.md5(loginDTO.getPassword()+salt);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",loginDTO.getUserName()).
                eq("password",md5Password);
        UserEntity userEntity = baseMapper.selectOne(wrapper);
        if (null == userEntity) {
            return Result.fail(FailInfo.VALID_USER_PASSWORD);
        }
        log.info("用户信息为,{}",userEntity);
        String token = jwtUtil.generateJwtToken(userEntity.getUserName());
        log.info("生成的token信息为,{}",userEntity);
        redisTemplate.set(TOKEN_REDIS_KEY + userEntity.getUserId(),token,expireTime);
        return Result.success(token);
    }


    @Override
    public Boolean loginOut(HttpServletRequest request) {
        UserEntity userEntity = getUserInfo(request);
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
    public PageInfo<UserVO> getUserList(String userName, Integer pageNum, Integer pageSize) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        if (StrUtil.isNotBlank(userName)) {
            wrapper.like("user_name",userName);
        }
        wrapper.orderByDesc("user_id");
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> userEntities = baseMapper.selectList(wrapper);
        List<UserVO> userList = new ArrayList<>();
        userEntities.stream().forEach(x -> {
            List<String> roleIds = bindMapper.getRoleIdsByUserId(x.getUserId());
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(x,userVO);
            userVO.setRoleIds(roleIds);
            userList.add(userVO);
        });
        return new PageInfo<>(userList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result bindUserRoles(BindUserRoleDTO dto) {
        Long userId = dto.getUserId();
        UserEntity entity = baseMapper.selectById(userId);
        //绑定用户和角色之间的关系
        bindUserAndRoleId(userId,dto.getRoleIds());
        if (null == entity) {
            throw new ApiException("用户不存在");
        }
        return Result.success();
    }
    
    private void bindUserAndRoleId(Long userId, List<Long> roleIds) {
        if (CollectionUtil.isNotEmpty(roleIds)) {
            //删除之前该用户的所有角色
            bindMapper.deleteByUserId(userId);
            //保存新角色权限
            for (Long roleId : roleIds) {
                UserRoleBindEntity bindEntity = new UserRoleBindEntity();
                bindEntity.setUserId(userId);
                bindEntity.setRoleId(roleId);
                bindMapper.insert(bindEntity);
            }
        }
    }

    @Override
    public Result getInfo(HttpServletRequest request) {
        UserEntity userEntity = getUserInfo(request);
        //获取角色信息
        List<RoleEntity> roleList = roleMapper.getRoleByUserId(userEntity.getUserId());
        List<Long> roleIds =
                roleList
                        .stream()
                        .map(RoleEntity::getId)
                        .collect(Collectors.toList());
        //获取菜单信息
        List<MenuEntity> menuEntities = menuMapper.getMenusByRoleIds(roleIds);
        Map<String, Object> data = new HashMap<>(16);
        data.put("userName",userEntity.getUserName());
        List<String> roleNames =
                roleList
                        .stream()
                        .map(RoleEntity::getRoleName)
                        .collect(Collectors.toList());
        data.put("roles",roleNames);
        List<String> menuNames =
                menuEntities
                        .stream()
                        .map(MenuEntity::getMenuName)
                        .collect(Collectors.toList());
        data.put("menus",menuNames);
        data.put("avatar",userEntity.getFace());
        return Result.success(data);
    }
    
    
    private UserEntity getUserInfo (HttpServletRequest request) {
        String token = request.getHeader(bearer);
        String jwtToken = token.substring(headerValuePrefix.length());
        String useName = jwtUtil.getUserName(jwtToken);
        //获取用户信息
        UserEntity userEntity = baseMapper.getUserInfoByName(useName);
        if (null == userEntity) {
            throw new ApiException("用户不存在");
        }
        return userEntity;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserEntity update(UserDTO userInfo) {
        UserVO userVO = baseMapper.getUserInfo(userInfo.getUserId());
        if (null == userVO) {
            throw new ApiException("修改失败，用户不存在");
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userInfo,entity);
        String newPassword = userInfo.getPassword()+salt;
        entity.setPassword(SecureUtil.md5(newPassword));
        baseMapper.updateById(entity);
        //用户与角色绑定
        bindUserAndRoleId(entity.getUserId(),userInfo.getRoleIds());
        return entity;
    }

    @Override
    public Boolean delete(Long userId) {
        UserEntity entity = baseMapper.selectById(userId);
        entity.setDelFlag(DeleteEnums.DEL.getCode());
        baseMapper.updateById(entity);
        return true;
    }
}
