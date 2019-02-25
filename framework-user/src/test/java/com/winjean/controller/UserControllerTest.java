package com.winjean.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：Administrator
 * 创建时间：2018/10/19 15:03
 * 修改人：Administrator
 * 修改时间：2018/10/19 15:03
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */

// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的
@SpringBootTest
// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    //新增用户信息
    @Test
    public void test1InsertUser() throws Exception {
        JSONObject json = new JSONObject();
        json.put("user_id","uid0000");
        json.put("user_name","winjean");
        json.put("birthdate","20181018");
        json.put("sex","1");
        json.put("create_user","c_user");
        json.put("create_time","20181019 08:12:22");
        json.put("update_user","u_user");
        json.put("update_time","20181020 18:12:22");

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/insert").content(json.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    //批量新增用户信息
    @Test
    public void test2InsertUsers() throws Exception {
        JSONObject json1 = new JSONObject();
        json1.put("user_id","uid0001");
        json1.put("user_name","winjean");
        json1.put("birthdate","20181018");
        json1.put("sex","1");
        json1.put("create_user","c_user");
        json1.put("create_time","20181019 08:12:22");
        json1.put("update_user","u_user");
        json1.put("update_time","20181020 18:12:22");

        JSONObject json2 = new JSONObject();
        json2.put("user_id","uid0002");
        json2.put("user_name","winjean");
        json2.put("birthdate","20181018");
        json2.put("sex","1");
        json2.put("create_user","c_user");
        json2.put("create_time","20181019 08:12:22");
        json2.put("update_user","u_user");
        json2.put("update_time","20181020 18:12:22");

        JSONArray _arr = new JSONArray();
        _arr.add(json1);
        _arr.add(json2);

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/insertUsers").content(_arr.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }


    //删除用户信息
    @Ignore
    @Test
    public void test6DeleteUser() throws Exception {
        JSONObject json = new JSONObject();
        json.put("user_id","uid0000");
        json.put("user_name","winjean");
        json.put("birthdate","20181018");
        json.put("sex","1");
        json.put("create_user","c_user");
        json.put("create_time","20181019 08:12:22");
        json.put("update_user","u_user");
        json.put("update_time","20181020 18:12:22");

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/delete").content(json.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    //批量删除用户信息
    @Ignore
    @Test
    public void test7DeleteUsers() throws Exception {
        JSONObject json1 = new JSONObject();
        json1.put("user_id","uid0001");
        json1.put("user_name","winjean");
        json1.put("birthdate","20181018");
        json1.put("sex","1");
        json1.put("create_user","c_user");
        json1.put("create_time","20181019 08:12:22");
        json1.put("update_user","u_user");
        json1.put("update_time","20181020 18:12:22");

        JSONObject json2 = new JSONObject();
        json2.put("user_id","uid0002");
        json2.put("user_name","winjean");
        json2.put("birthdate","20181018");
        json2.put("sex","1");
        json2.put("create_user","c_user");
        json2.put("create_time","20181019 08:12:22");
        json2.put("update_user","u_user");
        json2.put("update_time","20181020 18:12:22");

        JSONArray _arr = new JSONArray();
        _arr.add(json1);
        _arr.add(json2);

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/deleteUsers").content(_arr.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    //更新用户信息
    @Test
    public void test3UpdateUser() throws Exception {
        JSONObject json = new JSONObject();
        json.put("user_id","uid0000");
        json.put("user_name","winjean");
        json.put("birthdate","20181018");
        json.put("sex","1");
        json.put("create_user","uid0000");
        json.put("create_time","20181019 08:12:22");
        json.put("update_user","u_user");
        json.put("update_time","20181020 18:12:22");

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/update").content(json.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    //查询批量用户信息
    @Ignore
    @Test
    public void test4SearchUsers() throws Exception {
        JSONObject json = new JSONObject();
        json.put("pageNum",3);
        json.put("pageSize",2);

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/searchUsers").content(json.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    //查询用户信息
    @Ignore
    @Test
    public void test5SearchUser() throws Exception {
        JSONObject json = new JSONObject();
        json.put("user_id","uid0002");
        json.put("user_name","winjean");
        json.put("birthdate","20181018");
        json.put("sex","1");
        json.put("create_user","c_user");
        json.put("create_time","20181019 08:12:22");
        json.put("update_user","u_user");
        json.put("update_time","20181020 18:12:22");

        MvcResult result = mockMvc.perform(post("http://localhost:8080/user/searchUser").content(json.toJSONString()).header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

}