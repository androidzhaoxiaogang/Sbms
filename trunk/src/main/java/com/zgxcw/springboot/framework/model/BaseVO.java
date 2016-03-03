package com.zgxcw.springboot.framework.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
/**
 * 基础VO,其他VO可扩展此VO
 * @author zhao yongping
 */
public class BaseVO implements Serializable{

  private static final long serialVersionUID = 5022828870775113213L;
  /**
   * 重写 toString 方法
   */
  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
  
}
