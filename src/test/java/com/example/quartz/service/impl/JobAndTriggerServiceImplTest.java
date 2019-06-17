package com.example.quartz.service.impl;

import com.example.quartz.dao.JobAndTriggerMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class JobAndTriggerServiceImplTest {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    @Test
    void getJobType() {
        List<Map<String,Object>> jobType = jobAndTriggerMapper.getJobType();
        System.out.println(jobType);
    }
}