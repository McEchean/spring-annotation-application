package com.whf.annotation.study;

import com.whf.annotation.study.config.RootConfig;
import com.whf.annotation.study.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
