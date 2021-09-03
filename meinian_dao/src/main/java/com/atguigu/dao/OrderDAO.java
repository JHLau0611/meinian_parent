package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-30 0:50
 */
public interface OrderDAO {

    Integer getOrder(Order order);

    void add(Order order);

    Map findById(Integer id);

    int getTodayOrderNumber(String today);

    int getTodayVisitsNumber(String today);

    int getThisWeekAndMonthOrderNumber(Map<String, Object> paramWeek);

    int getThisWeekAndMonthVisitsNumber(Map<String, Object> paramWeekVisit);

    List<Map<String, Object>> findHotSetmeal();
}
