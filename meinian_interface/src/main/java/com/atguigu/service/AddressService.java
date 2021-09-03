package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-09-01 20:05
 */
public interface AddressService {
    List<Address> findAll();

    void addAddress(Address address);

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);
}
