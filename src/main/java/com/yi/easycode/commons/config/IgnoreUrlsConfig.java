package com.yi.easycode.commons.config;

import com.yi.easycode.modules.sys.mapper.WhiteUrlMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yizhicheng
 * @ClassName IgnoreUrls
 * @Description 白名单设置
 * @Date 2020/12/23 20:05 下午
 **/
@Configuration
@Data
@Slf4j
public class IgnoreUrlsConfig {

    @Resource
    private WhiteUrlMapper whiteUrlMapper;

    @Bean
    public List<String> whiteUrls() {
        List<String> whiteUrls = whiteUrlMapper
                .selectList(null)
                .stream().map(x -> x.getUrl())
                .collect(Collectors.toList());
        log.info("接口白名单如下");
        whiteUrls.stream().forEach(x -> log.info(x));
        return whiteUrls;
    }

}
