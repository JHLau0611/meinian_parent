package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-28 1:54
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;


    @RequestMapping("/getSetmeal.do")
    public Result getSetmeal(){
        List<Setmeal> setmealList = setmealService.getSetmeal();

        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmealList);
    }

    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        Setmeal setmeal = setmealService.findById(id);

        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
