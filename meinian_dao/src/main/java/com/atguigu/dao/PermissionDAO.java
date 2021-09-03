package com.atguigu.dao;

import com.atguigu.pojo.Permission;

/**
 * @author JHLau
 * @create 2021-09-01 0:13
 */
public interface PermissionDAO {

    Permission getPermissionByRoleId(Integer roldId);
}
