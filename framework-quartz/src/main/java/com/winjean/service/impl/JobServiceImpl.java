package com.winjean.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winjean.job.CronJob;
import com.winjean.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/26 11:39
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 创建一个定时任务
     *
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void addCronJob(String jobName, String jobGroup, JSONObject json) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                log.info("job:" + jobName + " 已存在");
            } else {
                //构建job信息

                jobDetail = JobBuilder.newJob(CronJob.class).withIdentity(jobName, jobGroup).build();

                jobDetail.getJobDataMap().put("msg", json.getString("msg"));

                //表达式调度构建器
                String cronExpression = "0 0/10 * * * ?";
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger()
                        .startNow()
                        .withIdentity(jobName + "_trigger", jobGroup + "_trigger")
                        .withSchedule(scheduleBuilder)
                        .build();

                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            log.error(" JobServiceImpl addCronJob exception :{}",e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateJob(String jobName, String jobGroup, JSONObject json) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            Assert.notNull(jobDetail,"job:" + jobName + " 不存在");

            deleteJob(jobName, jobGroup);
            addCronJob(jobName, jobGroup, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");

            scheduler.pauseTrigger(triggerKey);
            log.info(" pause job: {} success ",jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复任务
     *
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void resumeJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");
            scheduler.resumeTrigger(triggerKey);
            log.info(" resume job: {} success ",jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
            scheduler.deleteJob(jobKey);
            log.info(" delete job: {} success ",jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONArray listJob()  {
        JSONArray array = new JSONArray();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());

            for(JobKey jobKey : jobKeys){
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getName();

                JSONObject json = new JSONObject();
                json.put("jobName",jobName);
                json.put("jobGroup",jobGroup);

                array.add(json);
            }
            log.info(" job count: {}, jobs: {} ",array.size(), array);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return array;
    }
}
