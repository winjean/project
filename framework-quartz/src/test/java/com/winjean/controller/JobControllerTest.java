package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.QuartzApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/2 9:56
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@SpringBootTest(classes = QuartzApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class JobControllerTest {

    private MockMvc mockMvc;

    private String url = "http://localhost:8080/quartz/";

    @Autowired
    private WebApplicationContext wac;

    @Before
    // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void startCronJob()throws Exception{

        JSONObject json = new JSONObject();
        json.put("jobName","jobName-1");
        json.put("jobGroup","test");
        json.put("msg","winjean");

        MvcResult result = mockMvc.perform(post(url+"cron")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void pauseJob()throws Exception{

        JSONObject json = new JSONObject();
        json.put("jobName","jobName-1");
        json.put("jobGroup","jobGroup-1");

        MvcResult result = mockMvc.perform(post(url+"pause")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void resumeJob()throws Exception{

        JSONObject json = new JSONObject();
        json.put("jobName","jobName-1");
        json.put("jobGroup","jobGroup-1");

        MvcResult result = mockMvc.perform(post(url+"resume")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void deleteJob()throws Exception{

        JSONObject json = new JSONObject();
        json.put("jobName","jobName-1");
        json.put("jobGroup","jobGroup-1");

        MvcResult result = mockMvc.perform(post(url+"delete")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void listJob()throws Exception{

        MvcResult result = mockMvc.perform(post(url+"list")
//                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }
}
