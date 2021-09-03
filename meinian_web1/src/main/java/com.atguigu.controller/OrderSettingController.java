package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.constans.POIUtils;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-26 23:44
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload.do")
    public Result upload(MultipartFile excelFile) throws IOException {

        //1.解析excelFile 使用POI
        List<String[]> strList = POIUtils.readExcel(excelFile);
        //2.strList中的数据保存到orderSettingList
        List<OrderSetting> orderSettingList = new ArrayList<>();
        for (String[] strings : strList) {
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.setOrderDate(new Date(strings[0]));
            orderSetting.setNumber(Integer.parseInt(strings[1]));
            orderSettingList.add(orderSetting);
        }
        //3.保存orderSettingList数据到数据库
        orderSettingService.imports(orderSettingList);
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }


    @RequestMapping("/getOrderSettingByMonth.do")
    public Result getOrderSettingByMonth(String date){
        List<Map> list = orderSettingService.getOrderSettingByMonth(date);

        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }

    @RequestMapping("/editNumberByDate.do")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return  new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }


    }




























}

