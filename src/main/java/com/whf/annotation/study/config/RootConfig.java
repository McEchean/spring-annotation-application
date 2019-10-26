package com.whf.annotation.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@ComponentScan(
        value = {"com.whf.annotation.study"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})
)
@Configuration
public class RootConfig {
}
