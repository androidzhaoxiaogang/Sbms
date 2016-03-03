package com.zgxcw.springboot.framework.exception;
/**
 * <img src="oberon_lin.gif">
 * <p>
 * <font size = "1">
 * (C) 2016 com.oberon.lin All rights reserved.
 * <font size = "3">
 * <p>
 *      for process business exception.
 * <p>
 * Revision information:
 *      This is the first version of BusinessException
 * @author Lin Yu
 * @version 1.0, 2016-01-14
 */
public class BusinessException extends Exception implements IException{
  
  private static final long serialVersionUID = -44748695351668497L;
  protected String ivErrorCode;
  protected Object[] ivParams;

  /**
   * Exception constructor 
   * @param pErrorCode Error code in <code>String</code> to indicate the error
   * @param pParams Parameter to be filled into the message string
   */
  public BusinessException(String pErrorCode, Object[] pParams) {
    super();
    ivErrorCode = pErrorCode;
    ivParams = pParams;
  }

  /**
   *  Get the error code
   *  @return Error code
   */
  public String getErrorCode() {
    return ivErrorCode;
  }

  /**
   *  Set the error code
   *  @param  pErrorCode  Error code
   */
  public void setErrorCode(String pErrorCode) {
    ivErrorCode = pErrorCode;
  }

  /**
   *  Get the parameter object array
   *  @return Parameter in <code>Object[]</code>
   */
  public Object[] getParameters() {
    return ivParams;
  }

  /**
   *  Set the parameter object array
   *  @param  pParams Parameters
   */
  public void setParameters(Object[] pParams) {
    ivParams = pParams;
  }
}
