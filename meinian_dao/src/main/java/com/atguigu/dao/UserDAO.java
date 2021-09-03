package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @author JHLau
 * @create 2021-09-01 0:04
 */
public interface UserDAO {
    User loadUserByUsername(String username);
}
