package com.zgxcw.springboot.demo.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zgxcw.springboot.demo.mybatis.model.Country;
import com.zgxcw.springboot.framework.exception.BusinessException;
import com.zgxcw.springboot.framework.service.BaseService;

/**
 * @author zhao yongping
 * @since 2016-01-12 10:11
 */
@Service
public interface CountryJdbcService extends BaseService<Country>{
	
	public List<Country> readerSelectAll() throws BusinessException;
	public List<Country> writerSelectAll() throws BusinessException;
    
}
