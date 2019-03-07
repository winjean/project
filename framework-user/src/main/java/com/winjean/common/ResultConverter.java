package com.winjean.common;

import com.alibaba.fastjson.JSONObject;


public class ResultConverter {
    //返回给调用服务的状态码
    public static final String INVOKE_SUCCESS_CODE = "200";
    public static final String AUDIT_FAILURE_CODE = "204";
    public static final String INVOKE_ERROR_CODE = "1102";
    public static final String RETURN_NULL_CODE = "1103";
    public static final String FORMAT_ERROR_CODE = "1104";

    //返回给调用服务的消息
    public static final String RETURN_NULL = " third interface return null ";
    public static final String INVOKE_SUCCESS = " invoke success ";
    public static final String INVOKE_ERROR = " invoke third interface error ";
    public static final String FORMAT_ERROR = " third interface return format error ";

    /**
     * 结果转换
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static String parseResult(String code,String msg,Object data){
        JSONObject result = new JSONObject();
        result.put("code",code);
        result.put("msg",msg);
        result.put("data",data);
        return  JSONObject.toJSONString(result);
    }

    public static String parseResult(String code,String msg){
        return  parseResult(code,msg,"");
    }

    public static String parseSuccess(JSONObject json){
        return  parseResult(json.getString("code"),json.getString("msg"),json.getString("data"));
    }

    public static String parseSuccess(){
        return  parseResult(INVOKE_SUCCESS_CODE, INVOKE_SUCCESS);
    }

    public static String parseError(JSONObject json){
        return  parseResult(json.getString("code"),json.getString("msg"),json.getString("data"));
    }

    public static String parseError(){
        return  parseResult(INVOKE_ERROR_CODE, INVOKE_ERROR);
    }
}
