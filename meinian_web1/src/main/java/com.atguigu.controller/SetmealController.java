package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constans.MessageConstant;
import com.atguigu.constans.QiniuUtils;
import com.atguigu.constans.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * @author JHLau
 * @create 2021-08-25 18:59
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

//    文件上传(七牛云)
    @RequestMapping("/upload.do")
    public Result upload(MultipartFile imgFile) throws IOException {
        //获取文件名字
        String filename = imgFile.getOriginalFilename();
        String fileNameSuff = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + "-" + System.currentTimeMillis() + fileNameSuff;
        QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
        //把图片的名字保存到redis中
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
        return new Result(true, MessageConstant.UPLOAD_SUCCESS,filename);
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

        return setmealService.findPage(queryPageBean);
    }

    @RequestMapping("/add.do")
    public Result add(@RequestBody Setmeal setmeal, Integer[] travelgroupIds){
        try {

            setmealService.add(setmeal,travelgroupIds);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);

        }
    }



}
