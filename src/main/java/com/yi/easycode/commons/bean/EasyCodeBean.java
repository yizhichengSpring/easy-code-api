package com.yi.easycode.commons.bean;

import com.yi.easycode.commons.config.IgnoreUrlsConfig;
import com.yi.easycode.commons.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yizhicheng
 * @ClassName EasyCodeBean
 * @Description 自定义的bean需要交给Spring管理
 * @Date 2020/12/20 8:49 下午
 **/
@Configuration
@Slf4j
public class EasyCodeBean implements WebMvcConfigurer {

    private Logger ignoreUrlLog = LoggerFactory.getLogger(IgnoreUrlsConfig.class);
    @Autowired
    private IgnoreUrlsConfig ignoreUrls;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ignoreUrlLog.info("接口白名单如下");
        ignoreUrls.getUrls().forEach(x -> ignoreUrlLog.info(x));
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(ignoreUrls.getUrls());
    }
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
