package com.zgxcw.springboot.demo.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.zgxcw.springboot.demo.mybatis.dao.CountryMapper;
import com.zgxcw.springboot.demo.mybatis.model.Country;
import com.zgxcw.springboot.demo.mybatis.service.CountryJdbcService;
import com.zgxcw.springboot.demo.mybatis.service.impl.CountryJdbcServiceImpl;
/**
 * service 单元测试
 * @author zhaoyp
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {
  
  @Mock
  private CountryMapper countryMapper;

  private CountryJdbcService countryService;

  @Before
  public void setUp() throws Exception {
    countryService = new CountryJdbcServiceImpl(countryMapper);
  }
  
  /**
   * 测试查询所有国家对象
   * @throws Exception
   */
  @Test
  public void getAllTest() throws Exception {
    List<Country> list = countryService.selectAll();
    System.out.println(list);
  }
  
  /**
   * 测试通过id查看国家信息
   * @throws Exception
   */
  @Test
  public void viewTest() throws Exception {
    Country country =  countryService.selectById("1");
    System.out.println(country);
  }
  
  /**
   * 测试通过ID删除国家对象
   * @throws Exception
   */
  @Test
  public void deleteTest() throws Exception {
    countryService.deleteById("1");
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
    countryService.insert(country);
  }
}
