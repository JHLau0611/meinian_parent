package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-30 18:42
 */
public interface MemberService {
    Member getMemberByPhone(String telephone);

    void add(Member member);

    List<Integer> getMemberCountByMonths(List<String> months);
}
