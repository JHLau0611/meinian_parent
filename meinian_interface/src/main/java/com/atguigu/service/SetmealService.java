package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-25 19:40
 */
public interface SetmealService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Setmeal setmeal, Integer[] travelgroupIds);

    //移动端查询套餐列表
    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);

    List<Map> getSetmealCountNames();
}
