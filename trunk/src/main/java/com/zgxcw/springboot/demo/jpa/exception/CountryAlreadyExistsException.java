package com.zgxcw.springboot.demo.jpa.exception;

/**
 * 自定义异常
 * @author zhao yongping
 *
 */
public class CountryAlreadyExistsException extends RuntimeException {

   
    private static final long serialVersionUID = -3955422006927663650L;
    /**
     * 对象已经存在异常
     * @param message
     */
    public CountryAlreadyExistsException(final String message) {
        super(message);
    }
}
