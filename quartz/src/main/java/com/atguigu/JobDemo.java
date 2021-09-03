package com.atguigu;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author JHLau
 * @create 2021-08-26 15:26
 */
public class JobDemo{

    public void run() throws JobExecutionException {

        System.out.println(new Date());


    }
}
