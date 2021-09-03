package com.atguigu.controller;

import com.atguigu.constans.MessageConstant;
import com.atguigu.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHLau
 * @create 2021-09-01 1:35
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUsername.do")
    public Result getUsername(){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user);
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }

    }

}
