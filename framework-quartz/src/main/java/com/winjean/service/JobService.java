package com.winjean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/26 11:39
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface JobService {
    /**
     * 添加一个定时任务
     * @param jobName
     * @param jobGroup
     */
    void addCronJob(String jobName, String jobGroup, JSONObject json);

    /**
     * 更新任务
     * @param jobName
     * @param jobGroup
     * @param json
     */
    void updateJob(String jobName, String jobGroup, JSONObject json);

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复任务
     * @param triggerName
     * @param triggerGroup
     */
    void resumeJob(String triggerName, String triggerGroup);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);

    /**
     * 查询job
     */
    JSONArray listJob();
}
