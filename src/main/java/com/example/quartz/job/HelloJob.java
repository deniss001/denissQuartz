package com.example.quartz.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HelloJob extends BaseJob {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

     String value1;
     String value2;

    public HelloJob(){

    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();


        System.err.println("Job_Name: " + key + " , " + dataMap.getString("value1") + "向" + dataMap.getString("value2")+"介绍，说："+dataMap.getString("value3"));

    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
