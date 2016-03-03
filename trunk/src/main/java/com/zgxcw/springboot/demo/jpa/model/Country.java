package com.zgxcw.springboot.demo.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Country {
    /**
     * 主键
     */
    @Id
    @NotNull
    @Size(max = 32)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * @param countryNname 名称
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
    public void setCountrycode(String countryCode) {
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