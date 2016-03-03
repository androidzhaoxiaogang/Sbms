package com.zgxcw.springboot.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import com.zgxcw.springboot.demo.mybatis.controller.CountryController;
import com.zgxcw.springboot.demo.mybatis.model.Country;
import com.zgxcw.springboot.demo.mybatis.service.CountryJdbcService;

/**
 * controller 单元测试
 * @author zhaoyp
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryControllerTest {
  @Mock
  private CountryJdbcService countryService;

  private CountryController countryController;

  @Before
  public void setUp() throws Exception {
    countryController = new CountryController(countryService);
  }
  
  /**
   * 测试查询所有国家对象
   * @throws Exception
   */
  @Test
  public void getAllTest() throws Exception {
    Country country = new Country();
    ModelAndView result = countryController.getAll(country);
    System.out.println(result);
  }
  
  /**
   * 测试通过id查看国家信息
   * @throws Exception
   */
  @Test
  public void viewTest() throws Exception {
    ModelAndView result = countryController.view("1");
    System.out.println(result);
  }
  
  /**
   * 测试通过ID删除国家对象
   * @throws Exception
   */
  @Test
  public void deleteTest() throws Exception {
    ModelAndView result = countryController.delete("1");
    System.out.println(result);
  }
  
  /**
   * 测试保存或修改国家对象
   * @throws Exception
   */
  @Test
  public void saveTest() throws Exception {
    Country country = new Country();
    country.setId("2");
    country.setCountryCode("AFtest");
    ModelAndView result = countryController.save(country);
    System.out.println(result);
  }
}
