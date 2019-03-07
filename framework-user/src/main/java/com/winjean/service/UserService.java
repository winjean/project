package com.winjean.service;

import com.winjean.common.PageResponse;
import com.winjean.model.entity.UserEntity;
import com.winjean.model.request.UserQueryRequest;
import com.winjean.model.response.UserQueryResponse;

import java.util.List;


public interface UserService {

    void insert(UserEntity user);

    void insert(List<UserEntity> users);

    void update(UserEntity user);

    void delete(UserEntity user);

    void delete(List<UserEntity> users);

    PageResponse<UserQueryResponse> searchUsers(UserQueryRequest req);

    UserEntity searchUser(UserEntity user);

}
