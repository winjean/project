package com.winjean.service;

import com.winjean.model.entity.UserEntity;
import org.springframework.data.domain.Page;


public interface UserService {

    UserEntity findById(Long id);

    UserEntity findByName(String name);

    Page<UserEntity> findAll(int pageNo, int pageSize);

    void save(UserEntity entity);

    void delete(Long id);

    void update(UserEntity entity);

}
