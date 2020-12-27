package com.yi.easycode.commons.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yizhicheng
 * @ClassName SwaggerConfig
 * @Description Swagger配置
 * @Date 2020/10/14 3:50 PM
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {


    @Bean(value = "basicInfoApi")
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(basicInfo())
                //.groupName("erp基础信息模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yi.easycode.modules"))
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo basicInfo() {
        return new ApiInfoBuilder()
                .title("接口api")
                .contact(new Contact("YI",
                        "https://myblog.yizhcheng.top",
                        "13141016707@163.com"))
                .description("代码生成器")
                .version("1.0")
                .termsOfServiceUrl("")
                .build();
    }
}

