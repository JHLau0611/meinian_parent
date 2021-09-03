package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-25 19:42
 */
public interface SetmealDAO {
    Page<Setmeal> findPageByQueryString(String queryString);

    void add(Setmeal setmeal);

    void insert_setmeal_travelgroup(Map map);

    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);

    List<Map> getSetmealCountNames();
}
