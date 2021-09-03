package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Address;
import com.atguigu.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;
    @RequestMapping("/findAllMaps")
    public Map findAllMaps(){
        Map<Object, Object> map = new HashMap<>();
        List<Address> addressList = addressService.findAll();
        // 存放经纬度
        List<Map> gridnMaps = new ArrayList<>();
        List<Map> nameMaps = new ArrayList<>();
        for(Address address:addressList){
            Map<String,Object> gridnMap = new HashMap<>();

            gridnMap.put("lng",address.getLng());
            gridnMap.put("lat",address.getLat());

            gridnMaps.add(gridnMap);

            Map<Object, Object> nameMap = new HashMap<>();
            nameMap.put("addressName",address.getAddressName());
            nameMaps.add(nameMap);
        }
        map.put("gridnMaps",gridnMaps);
        map.put("nameMaps",nameMaps);
        System.out.println(map);
        return map;
    }


    @RequestMapping("/addAddress.do")
    public Result addAddress(@RequestBody Address address){
        addressService.addAddress(address);
        return new Result(true,"添加成功");
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =  addressService.findPage(queryPageBean);
        return pageResult;
    }
//
//
    @RequestMapping("/deleteById.do")
    public Result deleteById(Integer id){
        addressService.deleteById(id);
        return new Result(true,"删除分店地址成功");
    }
}
