package com.winjean.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> extends BaseResponse {

    private int pageNum;

    private int pageSize;

    private long total;

    private List<T> list;

    public static JSONObject getResponse(int pageNum, int pageSize,int  total, List list){
        JSONObject response = getSuccessResponse();
        response.put("pageNum",pageNum);
        response.put("pageSize",pageSize);
        response.put("total",total);
        response.put("list",list);
        return response;
    }

}
