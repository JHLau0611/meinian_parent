package com.atguigu.dao;

import com.atguigu.pojo.Address;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-09-01 20:20
 */
public interface AddressDAO {
    List<Address> findALL();

    void addAddress(Address address);

    Page<Address> findPage(String queryString);

    void deleteById();
}
