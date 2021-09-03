package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-27 0:13
 */
public interface OrderSettingDAO {

    Integer getCountByOrderDate(@Param("orderDate") Date orderDate);

    void update(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(String date);

    OrderSetting getOrderSettingByOrderDate(Date orderDate);

    void editReservation(OrderSetting orderSetting);
}
