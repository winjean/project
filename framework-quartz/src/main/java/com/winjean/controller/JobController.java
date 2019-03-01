package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/26 11:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/quartztest")
public class JobController {
    @Autowired
    private JobService jobService;


    /**
     * 创建cron任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/cron",method = RequestMethod.POST)
    public String startCronJob(@RequestBody JSONObject json){
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
    @RequestMapping(value = "/pause",method = RequestMethod.POST)
    public String pauseJob(@RequestBody JSONObject json){
        jobService.pauseJob(json.getString("jobName"),json.getString("jobGroup"));
        return "pause job success";
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/resume",method = RequestMethod.POST)
    public String resumeJob(@RequestBody JSONObject json){
        jobService.resumeJob(json.getString("jobName"),json.getString("jobGroup"));
        return "resume job success";
    }

    /**
     * 删除务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    public String deleteJob(@RequestBody JSONObject json){
        jobService.deleteJob(json.getString("jobName"),json.getString("jobGroup"));
        return "delete job success";
    }
}
