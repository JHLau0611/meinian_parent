package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.UserDAO;
import com.atguigu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JHLau
 * @create 2021-08-31 23:34
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;


    @Override
    public User loadUserByUsername(String username) {
        return userDAO.loadUserByUsername(username);
    }
}
