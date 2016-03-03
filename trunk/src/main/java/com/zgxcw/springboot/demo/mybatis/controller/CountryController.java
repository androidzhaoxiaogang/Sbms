package com.zgxcw.springboot.demo.mybatis.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zgxcw.springboot.demo.mybatis.model.Country;
import com.zgxcw.springboot.demo.mybatis.service.CountryJdbcService;

/**
 * @author zhao yongping
 * @since 2016-01-12 10:11
 */
@Controller
@RequestMapping("/countries")
public class CountryController{
    
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);
    private final CountryJdbcService countryService;
    //@Resource(name="countryServiceImpl")
    //private CountryService countryService;
    @Inject
    public CountryController(final CountryJdbcService countryService) {
        this.countryService = countryService;
    }
    
    /**
     * 查询所有国家对象
     * @param country
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView getAll(Country country) {
        logger.debug("Received request to query all countries");
        List<Country> countryList = null;
        ModelAndView result = null;
        try {
          countryList = countryService.selectAll();
          result = new ModelAndView("index");
          result.addObject("pageInfo", new PageInfo<Country>(countryList));
          result.addObject("queryParam", country);
          result.addObject("page", country.getPage());
          result.addObject("rows", country.getRows());
          logger.debug("query all countries sucess");
        } catch (Exception e) {
          throw new RuntimeException("query all countries exception");
        }
        return result;
    }
    
    /**
     * 新增国家对象
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add() {
        logger.debug("Received request to add country");  
        ModelAndView result = null;
        try {
          result = new ModelAndView("view");
          result.addObject("country",new Country());
          logger.debug("add country success");  
        } catch (Exception e) {
          throw new RuntimeException("add the country exception");
        }
        return result;
    }
    
    /**
     * 通过id查看国家信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable String id) {
        logger.debug("Received request to view the country by id {}", id);   
        Country country = null;
        ModelAndView result = null;
        try {
          country = countryService.selectById(id);
          result = new ModelAndView("view");
          result.addObject("country", country);
          logger.debug("view the country by id {} sucess", id);
        } catch (Exception e) {
          throw new RuntimeException("view the country by id "+id+" exception");
        }
        return result;
    }
    
    /**
     * 通过ID删除国家对象
     * @param id
     * @param ra
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable String id) {
        logger.debug("Received request to delete the country by id {}", id); 
        ModelAndView result = null;
        try {
          countryService.deleteById(id);
          result = new ModelAndView("redirect:/countries");
          result.addObject("msg","删除成功!");
          logger.debug("delete the country by id {} sucess", id);  
        } catch (Exception e) {
          throw new RuntimeException("delete the country by id "+id+" exception");
        }
        return result;
    }

    /**
     * 保存或修改国家对象
     * @param country
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Country country) {
        logger.debug("Received request to create the {}", country);  
        ModelAndView result = null;
        try {
          countryService.insert(country);
          result = new ModelAndView("view");
          String msg = country.getId() == null ? "新增成功!" : "更新成功!";
          result.addObject("country", country);
          result.addObject("msg", msg);
          logger.debug("create or update the country {} sucess", country);  
        } catch (Exception e) {
          throw new RuntimeException("create or update the country  exception");
        }
        return result;
    }
    
    /**
     * 查询所有国家对象
     * 读库
     * @param country
     * @return
     */
    @RequestMapping(value = "/reader")
    public ModelAndView reader(Country country) {
        logger.debug("Received request to query all countries");
        List<Country> countryList = null;
        ModelAndView result = null;
        try {
          countryList = countryService.readerSelectAll();
          result = new ModelAndView("index");
          result.addObject("pageInfo", new PageInfo<Country>(countryList));
          result.addObject("queryParam", country);
          result.addObject("page", country.getPage());
          result.addObject("rows", country.getRows());
          logger.debug("query all countries sucess");
        } catch (Exception e) {
          throw new RuntimeException("query all countries exception");
        }
        return result;
    }
    
    /**
     * 查询所有国家对象
     * 写库
     * @param country
     * @return
     */
    @RequestMapping(value = "/writer")
    public ModelAndView writer(Country country) {
        logger.debug("Received request to query all countries");
        List<Country> countryList = null;
        ModelAndView result = null;
        try {
          countryList = countryService.writerSelectAll();
          result = new ModelAndView("index");
          result.addObject("pageInfo", new PageInfo<Country>(countryList));
          result.addObject("queryParam", country);
          result.addObject("page", country.getPage());
          result.addObject("rows", country.getRows());
          logger.debug("query all countries sucess");
        } catch (Exception e) {
          throw new RuntimeException("query all countries exception");
        }
        return result;
    }
}
