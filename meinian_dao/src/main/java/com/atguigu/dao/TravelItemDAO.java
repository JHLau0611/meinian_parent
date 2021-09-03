package com.atguigu.dao;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-23 18:19
 */
public interface TravelItemDAO {

    void add(TravelItem travelItem);

    Page<TravelItem> findPageByQueryString(String queryString);

    void delete(Integer id);

    Integer getCountByItemId(Integer id);

    TravelItem findTravelItemById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    //查询移动端跟团游关联的自由行
    List<TravelItem> findItemByGroupId(Integer groupId);

}
