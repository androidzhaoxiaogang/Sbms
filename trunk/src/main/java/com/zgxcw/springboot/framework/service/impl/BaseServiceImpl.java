package com.zgxcw.springboot.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zgxcw.springboot.framework.dao.BaseDao;
import com.zgxcw.springboot.framework.exception.BusinessException;
import com.zgxcw.springboot.framework.service.BaseService;
import com.zgxcw.springboot.framework.utils.PageInfoBaseDto;

public class BaseServiceImpl implements BaseService<Object>{

  @Resource(name="baseDao")
  private BaseDao<Object> baseDao;
  
  /**
   * 保存对象
   */
  @Override
  public String insert(Object obj) throws BusinessException {
    //合法性检查
    if(null == obj){
      throw new NullPointerException("the incoming object "+obj.getClass().getName()+" is null");
    }
    //执行写入数据库操作
    //返回执行结果
    return null;
  }

  @Override
  public void deleteById(String objId) throws BusinessException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String update(Object obj) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object selectById(String objId) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List selectAll() throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List selectListByCondition(Object obj) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PageInfoBaseDto selectPageList(Integer pageNum, Integer pageSize, Object obj)
      throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

}
