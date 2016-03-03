package com.zgxcw.springboot.demo.mybatis.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhao yongping
 * @since 2016-01-12 10:11
 * Spring Boot 默认配置的/** 映射到 /static（或/public ，/resources，/META-INF/resources），
 * 若想自定义映射文件路劲则继承WebMvcConfigurerAdapter 重写addResourceHandlers方法
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
