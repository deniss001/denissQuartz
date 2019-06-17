package com.example.quartz.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigInteger;

@Table(name = "qrtz_job_details")
public class JobAndTrigger {

    @Column(name = "JOB_NAME")
    private String JOB_NAME;

    @Column(name = "JOB_GROUP")
    private String JOB_GROUP;

    @Column(name = "JOB_CLASS_NAME")
    private String JOB_CLASS_NAME;

    @Column(name = "TRIGGER_NAME")
    private String TRIGGER_NAME;

    @Column(name = "TRIGGER_GROUP")
    private String TRIGGER_GROUP;

    @Column(name = "REPEAT_INTERVAL")
    private BigInteger REPEAT_INTERVAL;

    @Column(name = "TIMES_TRIGGERED")
    private BigInteger TIMES_TRIGGERED;

    @Column(name = "CRON_EXPRESSION")
    private String CRON_EXPRESSION;

    @Column(name = "TIME_ZONE_ID")
    private String TIME_ZONE_ID;

    public String getJOB_NAME() {
        return JOB_NAME;
    }

    public void setJOB_NAME(String JOB_NAME) {
        this.JOB_NAME = JOB_NAME;
    }

    public String getJOB_GROUP() {
        return JOB_GROUP;
    }

    public void setJOB_GROUP(String JOB_GROUP) {
        this.JOB_GROUP = JOB_GROUP;
    }

    public String getJOB_CLASS_NAME() {
        return JOB_CLASS_NAME;
    }

    public void setJOB_CLASS_NAME(String JOB_CLASS_NAME) {
        this.JOB_CLASS_NAME = JOB_CLASS_NAME;
    }

    public String getTRIGGER_NAME() {
        return TRIGGER_NAME;
    }

    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
    }

    public String getTRIGGER_GROUP() {
        return TRIGGER_GROUP;
    }

    public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
    }

    public BigInteger getREPEAT_INTERVAL() {
        return REPEAT_INTERVAL;
    }

    public void setREPEAT_INTERVAL(BigInteger REPEAT_INTERVAL) {
        this.REPEAT_INTERVAL = REPEAT_INTERVAL;
    }

    public BigInteger getTIMES_TRIGGERED() {
        return TIMES_TRIGGERED;
    }

    public void setTIMES_TRIGGERED(BigInteger TIMES_TRIGGERED) {
        this.TIMES_TRIGGERED = TIMES_TRIGGERED;
    }

    public String getCRON_EXPRESSION() {
        return CRON_EXPRESSION;
    }

    public void setCRON_EXPRESSION(String CRON_EXPRESSION) {
        this.CRON_EXPRESSION = CRON_EXPRESSION;
    }

    public String getTIME_ZONE_ID() {
        return TIME_ZONE_ID;
    }

    public void setTIME_ZONE_ID(String TIME_ZONE_ID) {
        this.TIME_ZONE_ID = TIME_ZONE_ID;
    }
}
