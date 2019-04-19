package com.winjean.service;

import com.winjean.model.entity.RoleEntity;
import com.winjean.model.entity.RoleResourceEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface RoleService {

    RoleEntity findById(Long id);

    RoleEntity findByName(String name);

    Page<RoleEntity> findAll(int pageNo, int pageSize);

    void save(RoleEntity entity);

    void save(List<RoleResourceEntity> entities);

    void delete(Long id);

    void deleteRoleResource(List<Long> ids);

    void update(RoleEntity entity);

}
