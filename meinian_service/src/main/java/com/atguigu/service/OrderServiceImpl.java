package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constans.DateUtils;
import com.atguigu.constans.MessageConstant;
import com.atguigu.dao.MemberDAO;
import com.atguigu.dao.OrderDAO;
import com.atguigu.dao.OrderSettingDAO;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-30 0:48
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderSettingDAO orderSettingDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private OrderDAO orderDAO;


    @Override
    public Result orderInfo(Map map) throws Exception {
//        1.判断当前的日期是否可以预约
//        2.判断当前的日期预约是否已满
//        4.进行预约
        Date orderDate = DateUtils.parseString2Date(map.get("orderDate").toString());

        OrderSetting orderSetting = orderSettingDAO.getOrderSettingByOrderDate(orderDate);

        if (orderSetting == null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        if (orderSetting.getReservations() >= orderSetting.getNumber()){
            return new Result(false, MessageConstant.ORDER_FULL);
        }
//        3.判断是否是会员,如果是会员,避免重复预约,不是会员,自动注册成会员,t_member 表插入一条记录
        String telephone = map.get("telephone").toString();
        Member member = memberDAO.getMemberByPhone(telephone);
        if (member == null){
            member = new Member();
            member.setName(map.get("name").toString());
            member.setSex(map.get("sex").toString());
            member.setIdCard(map.get("idCard").toString());
            member.setRegTime(new Date());
            member.setPhoneNumber(telephone);
            memberDAO.add(member);
        }

        //判断该用户是否在同一天重复预约过
        Integer memberId = member.getId();
        Integer setmealId = Integer.parseInt(map.get("setmealId").toString());
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setMemberId(memberId);
        order.setSetmealId(setmealId);
        Integer count = orderDAO.getOrder(order);
        if (count > 0){
            return new Result(false,MessageConstant.HAS_ORDERED);
        }

        //更新预约人数
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDAO.editReservation(orderSetting);

        //预约成功 下订单 保存order数据
        order = new Order();
        order.setOrderDate(orderDate);
        order.setMemberId(memberId);
        order.setSetmealId(setmealId);
        order.setOrderType(Order.ORDERTYPE_WEIXIN);
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        orderDAO.add(order);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order);

    }

    @Override
    public Map findById(Integer id) {

        try {
            Map map = orderDAO.findById(id);
            Date orderDate = (Date) map.get("orderDate");
            String orderDateStr = DateUtils.parseDate2String(orderDate);
            map.put("orderDate",orderDateStr);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
