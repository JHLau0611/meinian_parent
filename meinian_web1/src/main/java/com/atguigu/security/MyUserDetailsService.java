package com.atguigu.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author JHLau
 * @create 2021-08-31 23:22
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Reference
    private UserService userService;


    //根据用户名字查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.查询用户信息
        User user = userService.loadUserByUsername(username);
        if (user == null){
            return null;
        }
        //权限集合
        List<GrantedAuthority> lists = new ArrayList<>();
        //2.查询用户的权限信息
        Set<Role> roles = user.getRoles();//登录用户的所有角色
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();//角色的所有权限
            for (Permission permission : permissions) {
                String keyword = permission.getKeyword();
                lists.add(new SimpleGrantedAuthority(keyword));
            }
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),lists);
    }
}
