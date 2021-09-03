package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDAO;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-23 18:17
 */
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService{

    @Autowired
    private TravelItemDAO travelItemDAO;


    @Override
    public void add(TravelItem travelItem) {

        travelItemDAO.add(travelItem);

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页使用PageHelper
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page page = travelItemDAO.findPageByQueryString(queryPageBean.getQueryString());

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) {
        //查询表关系(当前自由行有没有关联到跟团游)
        Integer count = travelItemDAO.getCountByItemId(id);
        if (count > 0){
            throw new RuntimeException("不允许删除！");
        }
        travelItemDAO.delete(id);

    }

    @Override
    public TravelItem findTravelItemById(Integer id) {
        TravelItem travelItem = travelItemDAO.findTravelItemById(id);
        return travelItem;
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDAO.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDAO.findAll();
    }
}
