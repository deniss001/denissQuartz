package com.example.quartz;

import com.example.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.DateBuilder.evenHourDate;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;


public class QuartTest {

    private static Integer index =0;

    public static void main(String[] args) {
        try {

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


            // define the job and tie it to our HelloJob class
            JobDetail job1 = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("name", "Deniss")
                    .usingJobData("age", 22)
                    .usingJobData("index", index++)
                    .build();

            JobDetail job2 = newJob(HelloJob.class)
                    .withIdentity("job2", "group1")
                    .usingJobData("name", "Jack")
                    .usingJobData("age", 32)
                    .usingJobData("index", index++)
                    .build();

            JobDetail job3 = newJob(HelloJob.class)
                    .withIdentity("job3", "group1")
                    .usingJobData("name", "Bolni")
                    .usingJobData("age", 21)
                    .usingJobData("index", index++)
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger1 = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            Trigger trigger3 = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();


            //SimpleTrigger定位精确日期
            //指定时间开始触发，不重复
            SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                    .withIdentity("trigger4", "group1")
                    .startAt(new Date())                     // some Date
                    .forJob("job1", "group1")                 // identify job with name, group strings
                    .build();

            //指定时间触发，每隔10秒执行一次，重复10次
            trigger = newTrigger()
                    .withIdentity("trigger", "group1")
                    .startAt(new Date())  // if a start time is not given (if this line were omitted), "now" is implied
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)//时间
                            .withRepeatCount(10)) // note that 10 repeats will give a total of 11 firings ,次数
                    .forJob(job1) // identify job with handle to its JobDetail itself
                    .build();

            //立即触发，每个5分钟执行一次，直到22:00
            trigger = newTrigger()
                    .withIdentity("trigger7", "group1")
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(5)
                            .repeatForever())
                    .endAt(dateOf(22, 0, 0))
                    .build();

            //建立一个触发器，将在下一个小时的整点触发，然后每2小时重复一次
            trigger = newTrigger()
                    .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the default group
                    .startAt(evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
                    .withSchedule(simpleSchedule()
                            .withIntervalInHours(2)
                            .repeatForever())
                    // note that in this example, 'forJob(..)' is not called which is valid
                    // if the trigger is passed to the scheduler along with the job
                    .build();


            //CronTrigger,控制日期和时间
            //每隔二分钟，每天上午8点至下午5点之间：
            CronTrigger trigger10 = newTrigger()
                        .withIdentity("trigger10", "group1")
                        .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                        .forJob("myJob", "group1")
                        .build();


            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job1, trigger1);
            scheduler.scheduleJob(job2, trigger2);
            scheduler.scheduleJob(job3, trigger3);
            scheduler.start();
            Thread.sleep(100000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
