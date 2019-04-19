package com.winjean.service;

import com.winjean.model.entity.RoleEntity;
import org.springframework.data.domain.Page;


public interface RoleService {

    RoleEntity findById(int id);

    RoleEntity findByName(String name);

    Page<RoleEntity> findAll(int pageNo, int pageSize);

    void save(RoleEntity entity);

    void delete(int id);

    void update(RoleEntity entity);

}
