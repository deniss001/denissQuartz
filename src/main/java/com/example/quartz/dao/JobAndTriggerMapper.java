package com.example.quartz.dao;

import com.example.quartz.baseDao.BaseDao;
import com.example.quartz.entity.JobAndTrigger;

import java.util.List;
import java.util.Map;

public interface JobAndTriggerMapper extends BaseDao<JobAndTrigger> {

    List<JobAndTrigger> getJobAndTriggerDetails();

    List<Map<String,Object>> getJobType();
}
