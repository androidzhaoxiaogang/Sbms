package com.zgxcw.springboot.framework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zgxcw.springboot.framework.exception.BusinessException;
import com.zgxcw.springboot.framework.utils.PageInfoBaseDto;

/**
 * 对单表进行增删该查的基础service
 * @author zhao yongping
 * @since 2016-01-12 10:11
 */
@Service
public interface BaseService<T> {

    /**
     * 添加对象
     * @param obj
     * @return  添加后的对象ID
     */
    String insert(T obj) throws BusinessException;
    /**
     * 通过ID删除
     * @param objId
     * @throws BusinessException
     */
    void deleteById(String objId) throws BusinessException;

    /**
     * 修改对象
     * @param obj
     * @return 修改的对象ID
     */
    String update(T obj) throws BusinessException;

    /**
     * 通过对象主键获得对象
     * @param objId
     * @return 店铺类
     */
    T selectById(String objId) throws BusinessException;

    /**
     * 获取对象列表
     * @return 对象列表
     */
    List<T> selectAll() throws BusinessException;

    /**
     * 依据传入条件获取对象列表
     * @param obj
     * @return 符合传入条件的对象列表
     */
    List<T> selectListByCondition(
        T obj) throws BusinessException;

    /**
     * 分页获取店铺列表
     * @param pageNum :查询页数
     * @param pageSize :查询条数
     * @param 对象
     * @return 店铺类型列表
     */
    PageInfoBaseDto<T> selectPageList(Integer pageNum, Integer pageSize,
        T obj) throws BusinessException;
}
