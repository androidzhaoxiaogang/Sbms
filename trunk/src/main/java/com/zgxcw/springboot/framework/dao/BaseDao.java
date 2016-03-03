package com.zgxcw.springboot.framework.dao;

import java.util.List;

/**
 * 对单表进行增删改查的基础dao
 * @author zhao yongping
 * @since 2016-01-18 10:11
 */
public interface BaseDao<T> {
  /**
   * 插入
   * @param obj
   * @return
   */
  String insert(T obj);
  /**
   * 删除
   * @param objId
   */
  void deleteById(String objId);
  /**
   * 修改
   * @param obj
   * @return
   */
  String update(T obj);
  /**
   * 通过对象ID查询
   * @param objId
   * @return
   */
  T selectById(String objId);
  
  /**
   * 查询所有对象
   * @return
   */
  List<T> selectAll();
  /**
   * 根据查询条件查询对象列表
   * @param obj
   * @return
   */
  List<T> selectListByCondition(T obj);
}
