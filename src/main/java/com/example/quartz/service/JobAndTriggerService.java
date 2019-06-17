package com.example.quartz.service;

import com.example.quartz.entity.JobAndTrigger;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface JobAndTriggerService {

    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

    List<Map<String,Object>> getJobType();



}
