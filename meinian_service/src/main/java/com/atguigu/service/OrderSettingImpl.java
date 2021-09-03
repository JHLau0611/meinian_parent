package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDAO;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-27 0:11
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingImpl implements OrderSettingService{

    @Autowired
    private OrderSettingDAO orderSettingDAO;


    @Override
    public void imports(List<OrderSetting> orderSettingList) {
        for (OrderSetting orderSetting : orderSettingList) {
            Integer count = orderSettingDAO.getCountByOrderDate(orderSetting.getOrderDate());
            if (count > 0){
                orderSettingDAO.update(orderSetting);
            }else {
                orderSettingDAO.add(orderSetting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {

        List<OrderSetting> orderSettingList = orderSettingDAO.getOrderSettingByMonth(date);
        List<Map> mapList = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettingList) {
            Map map = new HashMap();
            map.put("date",orderSetting.getOrderDate().getDate());//年月日中的具体 日期
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());

            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        OrderSetting orderSettingdb = orderSettingDAO.getOrderSettingByOrderDate(orderSetting.getOrderDate());

        if (orderSettingdb == null){//当前日期没有预约过

            orderSettingDAO.add(orderSetting);
        //已经设置过了但是判断 页面修改的可预约人数和数据库已预约人数的大小
        }else if (orderSetting.getNumber() == orderSettingdb.getReservations()){

            throw new RuntimeException("已预约人数已超过上限! ");

        }else {

            orderSettingDAO.update(orderSetting);

        }
    }
}
