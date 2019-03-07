package com.winjean.service;

import com.winjean.model.po.UserEntity;
import com.winjean.model.vo.SearchUsersRequest;
import com.winjean.model.vo.SearchUsersResponse;

import java.util.List;


public interface UserService {

    void insert(UserEntity user);

    void insert(List<UserEntity> users);

    void update(UserEntity user);

    void delete(UserEntity user);

    void delete(List<UserEntity> users);

    SearchUsersResponse searchUsers(SearchUsersRequest req);

    UserEntity searchUser(UserEntity user);

}
