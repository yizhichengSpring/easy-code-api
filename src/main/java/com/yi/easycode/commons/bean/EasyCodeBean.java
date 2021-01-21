package com.yi.easycode.commons.bean;

import com.yi.easycode.commons.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author yizhicheng
 * @ClassName EasyCodeBean
 * @Description 自定义的bean需要交给Spring管理
 * @Date 2020/12/20 8:49 下午
 **/
@Configuration
@Slf4j
public class EasyCodeBean implements WebMvcConfigurer {
    @Autowired
    private List<String> whiteUrls;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("接口白名单如下");
        whiteUrls.stream().forEach(x -> log.info(x));
        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns(whiteUrls);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
