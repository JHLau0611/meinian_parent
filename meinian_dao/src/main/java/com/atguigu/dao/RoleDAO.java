package com.atguigu.dao;

import com.atguigu.pojo.Role;

/**
 * @author JHLau
 * @create 2021-09-01 0:10
 */
public interface RoleDAO {

    Role getRoleByUserId(Integer userId);

}
