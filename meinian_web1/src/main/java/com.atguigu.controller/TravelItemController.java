package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-23 13:41
 */
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    TravelItemService travelItemService;


    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")
    @RequestMapping("/add.do")
    public Result add(@RequestBody TravelItem travelItem){

            try {
                travelItemService.add(travelItem);
                return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
            }
    }


    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelItemService.findPage(queryPageBean);
        return pageResult;
    }

    @PreAuthorize("hasAuthority('ABC')")
    @RequestMapping("/delete.do")
    public Result delete(Integer id){
        try {
            travelItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findTravelItemById.do")
    public Result findTravelItemById(Integer id){
        TravelItem travelItem  = travelItemService.findTravelItemById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
    }

    @PreAuthorize("hasAuthority('TRAVELITEM_EDIT')")
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody TravelItem travelItem){
        try {
            travelItemService.edit(travelItem);
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findAll.do")
    public Result findAll(){
        List<TravelItem> travelItemList = travelItemService.findAll();

        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItemList);
    }
}
