package com.example.quartz.controller;


import com.example.quartz.entity.JobAndTrigger;
import com.example.quartz.job.BaseJob;
import com.example.quartz.service.JobAndTriggerService;
import com.github.pagehelper.PageInfo;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/job")
public class JobController {

    private static Logger log = LoggerFactory.getLogger(JobController.class);


    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    /**
     * 新增定时任务
     * @param jobClassName  //Job事件的类名全称
     * @param jobGroupName  //Job事件的组名
     * @param cronExpression //任务时间格式
     * @throws Exception
     */
    @PostMapping(value="/addjob")
    public void addjob(@RequestParam(value="jobClassName")String jobClassName,
                       @RequestParam(value="jobGroupName")String jobGroupName,
                       @RequestParam(value="cronExpression")String cronExpression,
                       @RequestParam(value = "jobName")String jobName,
                       @RequestParam(value = "createName")String createName,
                       @RequestParam(value = "doName")String doName,
                       @RequestParam(value = "message")String message) throws Exception
    {
        addJob(jobClassName, jobGroupName, cronExpression,jobName,createName,doName,message);
    }


    public void addJob(String jobClassName, String jobGroupName, String cronExpression,String jobName,String createName,String doName,String message)throws Exception{

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder
                .newJob(getClass(jobClassName).getClass())
                .withIdentity(jobName, jobGroupName)
                .usingJobData("value1",createName)
                .usingJobData("value2",doName)
                .usingJobData("value3",message)
                .build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }
    }


    /**
     * 暂停定时任务
     * @param jobClassName  //Job事件的类名全称
     * @param jobGroupName  //Job事件的组名
     * @throws Exception
     */
    @PostMapping(value="/pausejob")
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        jobPause(jobClassName, jobGroupName);
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception
    {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * 恢复定时任务
     * @param jobClassName  //Job事件的类名全称
     * @param jobGroupName  //Job事件的组名
     * @throws Exception
     */
    @PostMapping(value="/resumejob")
    public void resumejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        jobresume(jobClassName, jobGroupName);
    }

    public void jobresume(String jobClassName, String jobGroupName) throws Exception
    {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * 更新定时任务
     * @param jobClassName  //Job事件的类名全称
     * @param jobGroupName  //Job事件的组名
     * @param cronExpression    //时间表达式
     * @throws Exception
     */
    @PostMapping(value="/reschedulejob")
    public void rescheduleJob(@RequestParam(value="jobClassName")String jobClassName,
                              @RequestParam(value="jobGroupName")String jobGroupName,
                              @RequestParam(value="cronExpression")String cronExpression) throws Exception
    {
        jobreschedule(jobClassName, jobGroupName, cronExpression);
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception
    {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
        }
    }


    /**
     * 删除定时任务
     * @param jobClassName  //Job事件的类名全称
     * @param jobGroupName  //Job事件的组名
     * @throws Exception
     */
    @PostMapping(value="/deletejob")
    public void deletejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        jobdelete(jobClassName, jobGroupName);
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception
    {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * 查询所有任务
     * @param pageNum   //页码
     * @param pageSize  //页数
     * @return
     */
    @GetMapping(value="/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value="pageNum")Integer pageNum, @RequestParam(value="pageSize")Integer pageSize)
    {
        PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }

    public static BaseJob getClass(String classname) throws Exception
    {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.newInstance();
    }

}
