package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constans.RedisConstant;
import com.atguigu.dao.SetmealDAO;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-25 19:38
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{

    @Autowired
    private SetmealDAO setmealDAO;
    @Autowired
    private JedisPool jedisPool;


    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page page = setmealDAO.findPageByQueryString(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public void add(Setmeal setmeal, Integer[] travelgroupIds) {
        //1.添加套餐 返回最新的id
        setmealDAO.add(setmeal);
        //2.把数据库中图片的名字保存到redis
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        //3.添加关系
        add_setmeal_travelgroup(setmeal.getId(),travelgroupIds);

    }

    @Override
    public List<Setmeal> getSetmeal() {
        return setmealDAO.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDAO.findById(id);

    }

    @Override
    public List<Map> getSetmealCountNames() {
        return setmealDAO.getSetmealCountNames();
    }


    private void add_setmeal_travelgroup(Integer setmealId, Integer[] travelgroupIds) {
        for (Integer travelgroupId : travelgroupIds) {
            Map map = new HashMap<>();
            map.put("setmealId",setmealId);
            map.put("travelgroupId",travelgroupId);

            setmealDAO.insert_setmeal_travelgroup(map);
        }

    }
}
