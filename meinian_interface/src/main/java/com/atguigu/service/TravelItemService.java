package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-23 18:16
 */
public interface TravelItemService {

    public void add(TravelItem travelItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    TravelItem findTravelItemById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();
}
