package com.atguigu.clear;

import com.atguigu.constans.QiniuUtils;
import com.atguigu.constans.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author JHLau
 * @create 2021-08-26 18:20
 */
public class ClearQiniu {

    @Autowired
    private JedisPool jedisPool;

    public void clearImage(){

        Jedis jedis = jedisPool.getResource();
        Set<String> sdiff = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        for (String imgName : sdiff) {
            //1.删除七牛云中的图片
            QiniuUtils.deleteFileFromQiniu(imgName);
            //2.删除Redis中对应的七牛云的图片
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,imgName);
            System.out.println("删除成功!");
        }

    }
}
