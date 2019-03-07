package com.winjean.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class HttpClientUtil {

    // post form格式
    public static String postForm(String accessUrl, JSONObject _params){
        HttpEntity entity = null;
        try {
            Set<Map.Entry<String,Object>> entries = _params.entrySet();
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for(Map.Entry<String,Object > entry : entries){
                list.add(new BasicNameValuePair(entry.getKey(), URLEncoder.encode(entry.getValue().toString(), "UTF-8")));
            }
            entity = new UrlEncodedFormEntity(list, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return post(entity,accessUrl);
    }

    /**
     * json格式获取token post
     * @return
     */
    public static String postJson(String jsonStr,String accessUrl) {
        log.info("调用的接口为：" + accessUrl + " 参数为：" + jsonStr);
        StringEntity entity = new StringEntity(jsonStr, ContentType.create("application/json", "utf-8"));
        entity.setContentType("text/json");
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        return post(entity,accessUrl);
    }

    /**
     * 获取token post
     * @return
     */
    public static String post(HttpEntity entity, String accessUrl) {
        //实例化httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //实例化post方法
        HttpPost httpPost = new HttpPost(accessUrl);

        String content = "";
        try {
            //将参数给post方法
            httpPost.setEntity(entity);
            //提交的参数
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "utf-8");
            }else{
//                log.error("调用微信http接口失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    /**
     * 获取token 请求
     * @return
     */
    public static String get(String accessUrl) {
        //实例化httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(accessUrl);
        httpGet.setConfig(requestConfig);
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
            }else{
//                log.info("http请求异常");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return srtResult;
    }
}
