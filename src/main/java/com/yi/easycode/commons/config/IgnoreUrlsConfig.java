package com.yi.easycode.commons.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yizhicheng
 * @ClassName IgnoreUrls
 * @Description 白名单设置
 * @Date 2020/12/23 20:05 下午
 **/

@ConfigurationProperties(prefix = "ignore")
@Component
@Data
@Slf4j
public class IgnoreUrlsConfig {

    private List<String> urls;

}
