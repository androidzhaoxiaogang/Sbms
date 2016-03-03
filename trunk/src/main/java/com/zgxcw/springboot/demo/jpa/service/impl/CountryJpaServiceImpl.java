package com.zgxcw.springboot.demo.jpa.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zgxcw.springboot.demo.jpa.dao.CountryRepository;
import com.zgxcw.springboot.demo.jpa.model.Country;
import com.zgxcw.springboot.demo.jpa.service.CountryJpaService;
import com.zgxcw.springboot.framework.exception.BusinessException;
import com.zgxcw.springboot.framework.utils.PageInfoBaseDto;

/**
 * @author zhao yongping
 * @since 2016-01-12 10:11
 */
@Service
public class CountryJpaServiceImpl implements CountryJpaService{

    //@Resource(name="countryRepository")
    //private CountryRepository countryRepository;
    
    private final CountryRepository countryRepository;
    @Inject
    public CountryJpaServiceImpl(final CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    /**
     * 新增
     */
    public String insert(Country obj) throws BusinessException {
      countryRepository.save(obj);
      return null;
    }
    /**
     * 通过ID删除
     */
    public void deleteById(String objId) throws BusinessException {
      countryRepository.delete(Integer.parseInt(objId));
    }
    /**
     * 更新
     */
    public String update(Country obj) throws BusinessException {
      Country country = countryRepository.save(obj);
      if(null != country){
        return null == country.getId() ? null : String.valueOf(country.getId());
      }
      return null;
    }
    /**
     * 通过ID查询
     */
    public Country selectById(String objId) throws BusinessException {
      Country country = countryRepository.findOne(Integer.parseInt(objId));
      return country;
    }
    /**
     * 查询所有
     */
    public List<Country> selectAll() throws BusinessException {
      Iterable<Country> iter = countryRepository.findAll();
      if(null != iter){
        return (List<Country>) iter;
      }
      return null;
    }
    /**
     * 通过条件查询所有
     */
    public List<Country> selectListByCondition(Country obj) throws BusinessException {
      
      return null;
    }
    /**
     * 分页查询
     */
    public PageInfoBaseDto<Country> selectPageList(Integer pageNum, Integer pageSize, Country obj)
        throws BusinessException {
      
      return null;
    }
    
}
