package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author JHLau
 * @create 2021-08-31 23:33
 */
public interface UserService {
    User loadUserByUsername(String username);
}
