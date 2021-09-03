package com.atguigu.service;

import com.atguigu.pojo.User;
import com.atguigu.util.MD5Utils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JHLau
 * @create 2021-08-31 4:33
 */
@Component
public class UserService implements UserDetailsService {
    static Map<String, User> map =   new HashMap<String, User>();

    static {
        com.atguigu.pojo.User user1 =  new com.atguigu.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setTelephone("123");

        com.atguigu.pojo.User user2 =  new com.atguigu.pojo.User();
        user2.setUsername("zhangsan");
        user2.setPassword("123");
        user2.setTelephone("321");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

    /**
     * 根据用户名加载用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username"+username);
        //模拟根据用户名查询数据库
        com.atguigu.pojo.User userInDb = map.get(username);

        if (userInDb==null){
            //根据用户名没有查询到用户，抛出异常，表示登录名输入有误
            return  null;
        }
        //模拟数据库中的密码，后期需要查询数据库
        String passwordInDb ="{noop}" + userInDb.getPassword();
        //授权，后期需要改为查询数据库动态获得用户拥有的权限和角色
        List<GrantedAuthority> lists = new ArrayList<>();
        lists.add(new SimpleGrantedAuthority("add"));
        lists.add(new SimpleGrantedAuthority("delete"));
        lists.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //public User(String username, String password, Collection<? extends GrantedAuthority > authorities)
        //返回User，
        //参数一：存放登录名，
        //参数二：存放数据库查询的密码（数据库获取的密码，默认会和页面获取的密码进行比对，成功跳转到成功页面，失败回到登录页面，并抛出异常表示失败）
        //参数三：存放当前用户具有的权限集合
//        String pwd = MD5Utils.md5(userInDb.getPassword());
        String pwd = new BCryptPasswordEncoder().encode(userInDb.getPassword());
        return new org.springframework.security.core.userdetails.User(username,pwd,lists);//注意：框架提供的User类：org.springframework.security.core.userdetails.User
    }
}
