package com.zgxcw.springboot.demo.mybatis.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mongodb.MongoClient;
import com.zgxcw.springboot.demo.mybatis.dao.CountryMapper;
import com.zgxcw.springboot.demo.mybatis.model.Country;
import com.zgxcw.springboot.demo.mybatis.service.CountryJdbcService;
import com.zgxcw.springboot.framework.datasource.annotation.DataSource;
import com.zgxcw.springboot.framework.datasource.enums.DataSourceType;
import com.zgxcw.springboot.framework.exception.BusinessException;
import com.zgxcw.springboot.framework.utils.PageInfoBaseDto;

/**
 * @author zhao yongping
 * @since 2016-01-12 10:11
 */
@Service
public class CountryJdbcServiceImpl implements CountryJdbcService{

    //@Resource(name="countryMapper")
    //private CountryMapper countryMapper;
    private final CountryMapper countryMapper;
    
    @Inject
    public CountryJdbcServiceImpl(final CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }
    
    /**
     * 增加
     */
    @Override
    public String insert(Country obj) throws BusinessException {
      //校验必输项
      if(null == obj){
        throw new NullPointerException("the incoming object is null");
      }
      String result = countryMapper.insert(obj);
      return result;
    }
    /**
     * 通过ID删除
     */
    @Override
    public void deleteById(String objId) throws BusinessException {
      if(null == objId || "".equals(objId.trim())){
        throw new NullPointerException("the object's id is null or empty");
      }
      countryMapper.deleteById(objId);
    }
    /**
     * 修改
     */
    @Override
    public String update(Country obj) throws BusinessException {
      //校验必输项
      if(null == obj){
        throw new NullPointerException("the incoming object is null");
      }
      String result = countryMapper.update(obj);
      return result;
    }
    /**
     * 通过ID查询
     */
    @Override
    public Country selectById(String objId) throws BusinessException {
      if(null == objId || "".equals(objId.trim())){
        throw new NullPointerException("the object's id is null or empty");
      }
      Country obj = countryMapper.selectById(objId);
      return obj;
    }
    /**
     * 查询所有
     */
    @Override
    public List<Country> selectAll() throws BusinessException {
      List<Country> list = countryMapper.selectAll();
      return list;
    }
    /**
     * 通过条件查询
     */
    @Override
    public List<Country> selectListByCondition(Country obj) throws BusinessException {
      //校验必输项
      if(null == obj){
        throw new NullPointerException("the incoming object is null");
      }
      List<Country> list = countryMapper.selectListByCondition(obj);
      return list;
    }
    /**
     * 分页查询
     */
    @Override
    public PageInfoBaseDto<Country> selectPageList(Integer pageNum, Integer pageSize, Country obj)
        throws BusinessException {
      if (pageNum < 1 || pageSize < 1) {
        throw new IllegalArgumentException("查询页数和查询条数必须大于0");
      }
      // 开始查询
      PageHelper.startPage(pageNum, pageSize);
      List<Country> countryList = null;
      try {
        countryList = countryMapper.selectListByCondition(obj);
      } catch (Exception ex) {
        throw new RuntimeException("获取店铺列表失败！");
      }
      PageInfo<Country> pageInfo = new PageInfo<Country>(countryList);
      
      PageInfoBaseDto<Country> pageInfoBeanDto = new PageInfoBaseDto<Country>();

      BeanUtils.copyProperties(pageInfo, pageInfoBeanDto);
      pageInfoBeanDto.setList(countryList);
      return pageInfoBeanDto;
    }

    /**
     * 读测试
     */
	@Override
	@DataSource(DataSourceType.READ)
	public List<Country> readerSelectAll() throws BusinessException {
		List<Country> list = countryMapper.selectAll();
	      return list;
	}

	/**
	 * 写测试
	 */
	@Override
	@DataSource
	public List<Country> writerSelectAll() throws BusinessException {
		List<Country> list = countryMapper.selectAll();
	    return list;
	}

    
}
