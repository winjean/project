package com.winjean.model.request;

import lombok.Data;


@Data
public class UserQueryRequest {

    private int pageNum;

    private int pageSize;
}
