package com.winjean.model.vo;

import com.winjean.model.po.UserEntity;
import lombok.Data;

import java.util.List;



@Data
public class SearchUsersResponse {
    private int pageNum;
    private int pageSize;
    private long total;

    private List<UserEntity> users;
}
