package com.winjean.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 类描述：
 * 创建人：winjean
 * 创建时间：2018/10/9 10:49
 *
 * @version V 1.0
 */
public class BaseResponse {
    //返回给调用服务的状态码
    private static final String INVOKE_SUCCESS_CODE = "200";
    private static final String INVOKE_ERROR_CODE = "400";

    //返回给调用服务的消息
    private static final String INVOKE_SUCCESS = " invoke success ";
    private static final String INVOKE_ERROR = " invoke error ";

    public static JSONObject getSuccessResponse(){
        return getResponse(INVOKE_SUCCESS_CODE,INVOKE_SUCCESS);
    }

    public static JSONObject getSuccessResponse(Object data){
        return getResponse(INVOKE_SUCCESS_CODE,INVOKE_SUCCESS,data);
    }

    public static JSONObject getFailureResponse(){
        return getResponse( INVOKE_ERROR_CODE, INVOKE_ERROR);
    }

    public static JSONObject getFailureResponse(Object data){
        return getResponse( INVOKE_ERROR_CODE, INVOKE_ERROR, data);
    }

    public static JSONObject getResponse(String code, String msg,Object data){
        JSONObject response = new JSONObject();
        response.put("code",code);
        response.put("msg",msg);
        if(data != null){
            response.put("data",data);
        }
        return response;
    }

    public static JSONObject getResponse(String code, String msg){
        return getResponse(code, msg, null);
    }
}
