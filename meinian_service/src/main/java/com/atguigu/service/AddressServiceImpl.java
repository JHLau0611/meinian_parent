package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.AddressDAO;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-09-01 20:05
 */
@Service(interfaceClass = AddressService.class)
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDAO addressDAO;


    @Override
    public List<Address> findAll() {
        return addressDAO.findALL();
    }

    @Override
    public void addAddress(Address address) {
        addressDAO.addAddress(address);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Address> page = addressDAO.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public void deleteById(Integer id) {
        addressDAO.deleteById();
    }
}
