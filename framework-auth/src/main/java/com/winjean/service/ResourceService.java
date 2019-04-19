package com.winjean.service;

import com.winjean.model.entity.ResourceEntity;
import org.springframework.data.domain.Page;


public interface ResourceService {

    ResourceEntity findById(int id);

    ResourceEntity findByName(String name);

    Page<ResourceEntity> findAll(int pageNo, int pageSize);

    void save(ResourceEntity entity);

    void delete(int id);

    void update(ResourceEntity entity);

}
