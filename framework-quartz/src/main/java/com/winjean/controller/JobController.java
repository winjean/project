package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/26 11:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/quartz")
public class JobController {
    @Autowired
    private JobService jobService;


    /**
     * 创建cron任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @PostMapping("/cron")
    public Object startCronJob(@RequestBody JSONObject json){
        String jobName = json.getString("jobName");
        String jobGroup = json.getString("jobGroup");

        jobService.addCronJob(jobName,jobGroup,json);
        return "create cron task success";
    }

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @PostMapping("/pause")
    public Object pauseJob(@RequestBody JSONObject json){
        jobService.pauseJob(json.getString("jobName"),json.getString("jobGroup"));
        return "pause job success";
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @PostMapping("/resume")
    public Object resumeJob(@RequestBody JSONObject json){
        jobService.resumeJob(json.getString("jobName"),json.getString("jobGroup"));
        return "resume job success";
    }

    /**
     * 删除任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @PutMapping("/delete")
    public Object deleteJob(@RequestBody JSONObject json){
        jobService.deleteJob(json.getString("jobName"),json.getString("jobGroup"));
        return "delete job success";
    }

    /**
     * 查询任务
     * @return
     */
    @GetMapping("/list")
    public Object listJob(){
        return jobService.listJob();
    }
}
