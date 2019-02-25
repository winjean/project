package com.winjean.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * Copyright (C), 2018-2088
 * FileName: DictionaryService
 * Author:   qiuyujiao
 * Date:     2018/7/12 15:51
 * Description: rest请求工具类
 * History:
 * <author>          <time>           <version>          <desc>
 * qiuyujiao        2018/7/12 15:51    v1.0               新增
 */
@Slf4j
public class RestTemplateUtil {

    private static RestTemplate restTemplate;

    public static RestTemplate getRestTemplate(){
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    /**
     * get请求
     * @param requestUrl 请求的地址
     * @return
     */
    public static String getRequest(String requestUrl) {
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
        //通过responseEntity对象取状态码判断异常
        String resultStr = responseEntity.getBody();
        return resultStr;

    }

    /**
     * post请求
     * @param requestUrl 请求地址
     * @param paramMap 请求参数
     * @return
     */
    public static String postRequest(String requestUrl,Map<String, Object> paramMap) {
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrl,paramMap,String.class);
        //通过responseEntity对象取状态码判断异常
        String resultStr = responseEntity.getBody();
        return resultStr;
    }



    public static void main(String[] args) {
        String access_token = "11_I03kUZ9F6ntMTBWpi8fJrvwGpi3C7BU_1Y9-NIfa04DAzavOxLHqlP0gaQIObrgGLtzqcD3i67vWBxr-auvxz8s6--mNG4J-beQw9F3O7vSctLTCDIRXoy-FqPtpQTsJcDfBSNVZaJSZaG1qTVXhAIAZBD";
        String requestUrl = "https://api.weixin.qq.com/datacube/getarticlesummary";
        requestUrl+="?access_token="+access_token;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("begin_date","2018-06-08");
        paramMap.put("end_date","2018-06-08");
        System.out.println("返回结果==="+postRequest(requestUrl,paramMap));

    }




}
