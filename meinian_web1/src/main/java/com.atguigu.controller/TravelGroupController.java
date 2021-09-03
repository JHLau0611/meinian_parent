package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-24 19:09
 */
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;


    @RequestMapping("/add.do")
    public Result add(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds){
        try{

            travelGroupService.add(travelGroup,travelItemIds);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);

        }catch (Exception e){

            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);

        }
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

        return travelGroupService.findPage(queryPageBean);

    }

    @RequestMapping("/findTravelGroupById.do")
    public Result findTravelItemById(Integer id){
        TravelGroup travelGroup = travelGroupService.findTravelGroupById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);

    }

    @RequestMapping("/getItemIdByGroupId.do")
    public Result getItemIdByGroupId(Integer id){
        List<Integer> ids = travelGroupService.getItemIdByGroupId(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,ids);
    }

    @RequestMapping("/edit.do")
    public Result edit(@RequestBody TravelGroup travelGroup, Integer[] ids){

        try {
            travelGroupService.edit(travelGroup,ids);
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findAll.do")
    public Result findAll(){
        List<TravelGroup> travelGroupList = travelGroupService.findAll();

        return new Result(true,"查询跟团游成功",travelGroupList);
    }
}


