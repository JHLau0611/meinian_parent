package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-24 19:14
 */
public interface TravelGroupDAO {
    void add(TravelGroup travelGroup);

    void Insert_Group_Item(Map map);

    Page<TravelGroup> findPageByQueryString(String queryString);

    TravelGroup findTravelGroupById(@Param("id") Integer id);

    List<Integer> getItemIdByGroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void del_Grop_ItemById(Integer id);

    List<TravelGroup> findAll();

    //查询移动端关联套餐游的跟团游
    List<TravelGroup> findGroupBySetmealId(Integer setmealId);


}
