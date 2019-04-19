package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.PageResponse;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.request.UserInsertRequest;
import com.winjean.model.response.UserQueryResponse;

import java.util.List;


public interface UserService {

    void insert(UserInsertRequest user);

    void insert(List<UserInsertRequest> users);

    void update(UserEntity user);

    void delete(String id);

    void delete(List<JSONObject> users);

    PageResponse<UserQueryResponse> searchUsers(JSONObject req);

    JSONObject searchUser(UserEntity user);

}
