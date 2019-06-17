package com.example.quartz.service.impl;

import com.example.quartz.dao.JobAndTriggerMapper;
import com.example.quartz.entity.JobAndTrigger;
import com.example.quartz.service.JobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerService {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    @Override
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getJobType() {
        return jobAndTriggerMapper.getJobType();
    }
}
