package com.winjean.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;

public class BaseResponse {

    //返回给调用服务的状态码
    private static final int INVOKE_SUCCESS_CODE = HttpStatus.SC_OK;
    private static final int OTHER_ERROR_CODE = 999;

    //返回给调用服务的消息
    private static final String INVOKE_SUCCESS = " invoke success ";
    private static final String INVOKE_ERROR = " invoke error ";

    public static JSONObject getSuccessResponse(){
        return getResponse(HttpStatus.SC_OK,INVOKE_SUCCESS);
    }

    public static JSONObject getSuccessResponse(String msg){
        return getResponse(HttpStatus.SC_OK,msg);
    }

    public static JSONObject getSuccessResponse(String msg, Object data){
        return getResponse(HttpStatus.SC_OK,msg,data);
    }

    public static JSONObject getSuccessResponse(Object data){
        return getResponse(HttpStatus.SC_OK,INVOKE_SUCCESS,data);
    }

    public static JSONObject getFailureResponse(){
        return getResponse( HttpStatus.SC_INTERNAL_SERVER_ERROR, INVOKE_ERROR);
    }

    public static JSONObject getFailureResponse(Object data){
        return getResponse( HttpStatus.SC_INTERNAL_SERVER_ERROR, INVOKE_ERROR, data);
    }

    public static JSONObject getFailureResponse(String msg){
        return getResponse( HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);
    }

    public static JSONObject getFailureResponse(String msg, Object data){
        return getResponse( HttpStatus.SC_INTERNAL_SERVER_ERROR, msg, data);
    }

    public static JSONObject getResponse(int code, String msg,Object data){
        JSONObject response = new JSONObject();
        response.put("code",code);
        response.put("msg",msg);
        if(data != null){
            response.put("data",data);
        }
        return response;
    }

    public static JSONObject getResponse(int code, String msg){
        return getResponse(code, msg, null);
    }
}
