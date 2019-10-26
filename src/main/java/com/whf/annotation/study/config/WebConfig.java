package com.whf.annotation.study.config;

import com.whf.annotation.study.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(
        value = {"com.whf.annotation.study"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class}),
        useDefaultFilters = false
)
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * 注册静态资源解析器
     * 将springmvc处理不了的请求交给tomcat
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/src/main/webapp/views/",".jsp");
    }

}
