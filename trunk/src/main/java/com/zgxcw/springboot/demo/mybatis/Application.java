package com.zgxcw.springboot.demo.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zgxcw.springboot.framework.datasource.config.DataSourceDynamicRegister;


/**
 * @author zhao yongping
 * @since 2016-01-12 10:10
 */
@Controller
@Import(DataSourceDynamicRegister.class)
@SpringBootApplication
@ComponentScan({"com.zgxcw.springboot.demo.mybatis","com.zgxcw.springboot.framework"})
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    String home() {
        return "redirect:countries/index";
    }
}
