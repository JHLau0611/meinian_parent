package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.constans.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-30 18:27
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping("/check.do")
    public Result check(@RequestBody Map map){
        String telephone = map.get("telephone").toString();
        String validateCode = map.get("validateCode").toString();

        //根据手机号查询用户是否为会员,如果不是会员自动注册为会员
        Member member = memberService.getMemberByPhone(telephone);
        if (member == null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberService.add(member);
        }


        String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);

        if (telephone == null || "".equals(telephone) && validateCode == null || "".equals(validateCode)){
            return new Result(false, MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
        }

        if (redisCode == null || !validateCode.equals(redisCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        if (validateCode.equals(redisCode)){
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }







        return null;
    }
}
