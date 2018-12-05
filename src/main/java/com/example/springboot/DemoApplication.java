package com.example.springboot;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import io.swagger.models.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 外部Tomcat启动继承SpringBootServletInitializer
 * @author linghongkang
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource cfgSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsCfg = new CorsConfiguration();
        corsCfg.addAllowedOrigin("*");
        corsCfg.addAllowedHeader("*");
        corsCfg.setAllowedMethods(Lists.newArrayList("GET", "POST", "OPTIONS"));
        corsCfg.setAllowCredentials(true);
        corsCfg.setMaxAge(3600L);
        cfgSource.registerCorsConfiguration("/**", corsCfg);
        return new CorsFilter(cfgSource);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                // 选择那些路径和api会生成document
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                //不显示错误的接口地址
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                // 对根下所有路径进行监控
//                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("这是我的接口文档")
                .description("这是SWAGGER_2生成的接口文档")
                .termsOfServiceUrl("NO terms of service")
//                .license("The Apache License, Version 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("v1.0")
                .build();
    }
}
