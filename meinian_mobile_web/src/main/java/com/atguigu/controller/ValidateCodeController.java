package com.atguigu.controller;

import com.atguigu.constans.MessageConstant;
import com.atguigu.constans.RedisMessageConstant;
import com.atguigu.constans.SMSUtils;
import com.atguigu.constans.ValidateCodeUtils;
import com.atguigu.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author JHLau
 * @create 2021-08-29 19:53
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;


    //下订单时发送验证码
    @RequestMapping("/send4Order.do")
    public Result send4Order(String telephone) throws Exception {
        try {
            //生成验证码
            Integer code = ValidateCodeUtils.generateValidateCode(4);
            SMSUtils.sendShortMessage(telephone,code.toString());

            //使用Redis保存验证码
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,60*60*24,code.toString());
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);

        }
    }

    //登陆时发送验证码
    @RequestMapping("/send4Login.do")
    public Result send4Login(String telephone){
        try {
            Integer code = ValidateCodeUtils.generateValidateCode(4);
            SMSUtils.sendShortMessage(telephone,code.toString());
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN,60*60*24,code.toString());
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);

        }
    }
}
