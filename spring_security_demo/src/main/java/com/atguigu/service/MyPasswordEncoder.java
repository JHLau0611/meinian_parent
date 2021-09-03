package com.atguigu.service;

import com.atguigu.util.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author JHLau
 * @create 2021-08-31 21:38
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    //对明文加密rawPassword
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Utils.md5(rawPassword.toString());
    }

    //密码比较 rawPassword 明文 客户端输入的密码和数据库的密码比较
    //encodedPassword从数据库查询出来的密码 密文
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword.toString()).equals(encodedPassword);
    }
}
