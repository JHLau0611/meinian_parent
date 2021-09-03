package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constans.DateUtils;
import com.atguigu.dao.MemberDAO;
import com.atguigu.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JHLau
 * @create 2021-08-30 18:44
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member getMemberByPhone(String telephone) {
        return memberDAO.getMemberByPhone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDAO.add(member);
    }

    @Override
    public List<Integer> getMemberCountByMonths(List<String> months) {
        List<Integer> countList = new ArrayList<>();

        for (String month : months) {//eg: month = 20xx-1 lastDatOfMonth 就等于 20xx-1-31
            String lastDayOfMonth = DateUtils.getLastDayOfMonth(month);
            countList.add(memberDAO.getMemberCountByMonths(lastDayOfMonth));
        }

        return countList;
    }
}
