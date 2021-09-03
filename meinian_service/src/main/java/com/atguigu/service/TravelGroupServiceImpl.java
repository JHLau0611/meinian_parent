package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDAO;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-24 19:13
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService{

    @Autowired
    private TravelGroupDAO travelGroupDAO;


    @Override
    public void add(TravelGroup travelGroup, Integer[] travelItemIds) {
        //1.添加跟团游 返回跟团游的id
        travelGroupDAO.add(travelGroup);
        //2.添加跟团游和自由行的关联关系
        addGroup_Item(travelGroup.getId(), travelItemIds);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page page = travelGroupDAO.findPageByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public TravelGroup findTravelGroupById(Integer id) {
        TravelGroup travelGroup = travelGroupDAO.findTravelGroupById(id);
        return travelGroup;
    }

    @Override
    public List<Integer> getItemIdByGroupId(Integer id) {

        return travelGroupDAO.getItemIdByGroupId(id);
    }

    @Override
    public void edit(TravelGroup travelGroup, Integer[] ids) {
        //1.修改跟团游
        travelGroupDAO.edit(travelGroup);
        //2.删除关系表数据(根据跟团游的id)
        travelGroupDAO.del_Grop_ItemById(travelGroup.getId());
        //3.添加关系
        addGroup_Item(travelGroup.getId(),ids);

    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDAO.findAll();
    }

    private void addGroup_Item(Integer groupId, Integer[] travelItemIds) {
        for (Integer travelItemId : travelItemIds) {
            Map map = new HashMap<>();
            map.put("groupId",groupId);
            map.put("itemId",travelItemId);
            travelGroupDAO.Insert_Group_Item(map);
        }
    }
}
