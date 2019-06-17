package com.example.quartz.job;


import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SendEmailJob extends BaseJob{

    private static Logger _log = LoggerFactory.getLogger(SendEmailJob.class);
    private String value1;
    private String value2;
    private String value3;

    public SendEmailJob(){

    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("Job_Name: " + key +" , "+dataMap.getString("value1")+"发送一封邮件给"+dataMap.getString("value2")+",内容为："+dataMap.getString("value3"));
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
