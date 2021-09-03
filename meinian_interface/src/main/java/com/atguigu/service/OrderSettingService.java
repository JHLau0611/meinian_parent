package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-27 0:11
 */
public interface OrderSettingService {

    void imports(List<OrderSetting> orderSettingList);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
