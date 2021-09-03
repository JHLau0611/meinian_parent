package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-30 0:46
 */
public interface OrderService {

    Result orderInfo(Map map) throws Exception;

    Map findById(Integer id);
}
