package com.winjean.service;

import com.winjean.model.entity.UserEntity;
import com.winjean.model.entity.UserRoleEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    UserEntity findById(Long id);

    UserEntity findByName(String name);

    Page<UserEntity> findAll(int pageNo, int pageSize);

    void save(UserEntity entity);

    void delete(Long id);

    void update(UserEntity entity);

    void save(List<UserRoleEntity> entities);

    void deleteUserRole(List<Long> ids);
}
