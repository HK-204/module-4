package com.example.manager;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {  // khai báo beans dành cho services/data
        return new Class<?>[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { // khai báo beans dành cho layer (controllers, views)
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() { // đường dẫn gốc mapping "/"
        return new String[] {"/"};
    }
}
