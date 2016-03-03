package com.zgxcw.springboot.demo.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.zgxcw.springboot.demo.jpa.model.Country;
/**
 * jpa实现
 * @author zhao yongping
 *
 */
public interface CountryRepository extends CrudRepository<Country,Integer> {

}
