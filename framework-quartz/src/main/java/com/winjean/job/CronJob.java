package com.winjean.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/26 11:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Slf4j
public class CronJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String jobGroup = jobExecutionContext.getJobDetail().getKey().getGroup();
        String msg = jobExecutionContext.getJobDetail().getJobDataMap().getString("msg");

        log.info("jobName:{}, jobGroup:{},msg:{}" , jobName, jobGroup, msg);
    }
}
