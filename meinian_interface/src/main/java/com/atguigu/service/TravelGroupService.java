package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-24 19:11
 */
public interface TravelGroupService {

    void add(TravelGroup travelGroup, Integer[] travelItemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    TravelGroup findTravelGroupById(Integer id);

    List<Integer> getItemIdByGroupId(Integer id);

    void edit(TravelGroup travelGroup, Integer[] ids);

    List<TravelGroup> findAll();
}
