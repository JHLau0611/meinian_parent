package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.constans.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Executable;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-30 0:26
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){

        String telephone = map.get("telephone").toString();
        Jedis jedis = jedisPool.getResource();
        String code = jedis.get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = map.get("validateCode").toString();
        if (code == null || !code.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        Result result = null;
        try {
            result = orderService.orderInfo(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }

    }

    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        Map map = orderService.findById(id);
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);

    }
}
