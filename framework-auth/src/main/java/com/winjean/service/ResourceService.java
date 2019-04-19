package com.winjean.service;

import com.winjean.model.entity.ResourceEntity;
import org.springframework.data.domain.Page;


public interface ResourceService {

    ResourceEntity findById(Long id);

    ResourceEntity findByName(String name);

    Page<ResourceEntity> findAll(int pageNo, int pageSize);

    void save(ResourceEntity entity);

    void delete(Long id);

    void update(ResourceEntity entity);

    Page<ResourceEntity> findResourcesByRole(Long roleId, int pageNo, int pageSize);

}
