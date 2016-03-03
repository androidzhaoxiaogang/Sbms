package com.zgxcw.springboot.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.zgxcw.springboot.framework.service.BaseService;

/**
 * Controller基类
 * 实现对单表的增删改查操作
 * @author zhao yongping
 * @since 2016-01-12 10:11
 */
@Controller
public class BaseController<T> {
    
  private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
  //private final BaseService<T> baseService;
  //@Resource(name="countryServiceImpl")
  //private CountryService countryService;
}
