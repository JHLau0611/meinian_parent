package com.atguigu.dao;

import com.atguigu.pojo.Member;

/**
 * @author JHLau
 * @create 2021-08-30 1:08
 */
public interface MemberDAO {
    Member getMemberByPhone(String telephone);

    void add(Member member);

    Integer getMemberCountByMonths(String lastDayOfMonth);

    int getTodayNewMember(String today);

    int getTotalMember();

    int getThisWeekAndMonthNewMember(String weekMonday);
}
