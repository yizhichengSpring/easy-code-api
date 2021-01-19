package com.yi.easycode.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.easycode.commons.enums.DeleteEnums;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.commons.result.Result;
import com.yi.easycode.modules.sys.dto.WhiteUrlDTO;
import com.yi.easycode.modules.sys.entity.WhiteUrlEntity;
import com.yi.easycode.modules.sys.mapper.WhiteUrlMapper;
import com.yi.easycode.modules.sys.service.WhiteUrlService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 白名单表 服务实现类
 * </p>
 *
 * @author yizhicheng
 * @since 2021-01-19
 */
@Service
public class WhiteUrlServiceImpl extends ServiceImpl<WhiteUrlMapper, WhiteUrlEntity> implements WhiteUrlService {


    @Override
    public PageInfo<WhiteUrlEntity> getWhiteUrlList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("del_flag", DeleteEnums.NORMAL.getCode());
        List<WhiteUrlEntity> whiteUrlEntities = baseMapper.selectList(wrapper);
        return new PageInfo<>(whiteUrlEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addWhiteUrl(WhiteUrlDTO dto) {
        WhiteUrlEntity whiteUrlEntity = new WhiteUrlEntity();
        BeanUtils.copyProperties(dto, whiteUrlEntity);
        baseMapper.insert(whiteUrlEntity);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateWhiteUrl(WhiteUrlDTO dto) {

        WhiteUrlEntity whiteUrlEntity = new WhiteUrlEntity();
        BeanUtils.copyProperties(dto, whiteUrlEntity);
        WhiteUrlEntity tmpWhiteUrlEntity =baseMapper.selectById(whiteUrlEntity.getId());
        if (null == tmpWhiteUrlEntity) {
            throw new ApiException("白名单不存在");
        }
        baseMapper.updateById(whiteUrlEntity);
        return Result.success();
    }

    @Override
    public Result deleteWhiteUrl(Long id) {
        WhiteUrlEntity whiteUrlEntity = baseMapper.selectById(id);
        if (null == whiteUrlEntity) {
            throw new ApiException("白名单不存在");
        }
        whiteUrlEntity.setDelFlag(DeleteEnums.DEL.getCode());
        baseMapper.updateById(whiteUrlEntity);
        return Result.success();
    }
}
