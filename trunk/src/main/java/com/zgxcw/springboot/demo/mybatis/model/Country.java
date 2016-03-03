package com.zgxcw.springboot.demo.mybatis.model;

import javax.persistence.Transient;

import com.zgxcw.springboot.framework.model.BaseVO;
/**
 * BaseVO中已经重写了 toString 方法
 * @author zhaoyp
 *
 */
public class Country extends BaseVO{
    
    private static final long serialVersionUID = -7733775188871316996L;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String countryName;

    /**
     * 代码
     */
    private String countryCode;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    /**
     * 获取主键
     *
     * @return Id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return countryName - 名称
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置名称
     *
     * @param countryname 名称
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 获取代码
     *
     * @return countryCode - 代码
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置代码
     *
     * @param countryCode 代码
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}