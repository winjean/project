package com.winjean.service;

import com.winjean.model.entity.DeptEntity;
import org.springframework.data.domain.Page;


public interface DeptService {

    DeptEntity findById(Long id);

    DeptEntity findByName(String name);

    Page<DeptEntity> findAll(int pageNo, int pageSize);

    void save(DeptEntity entity);

    void delete(Long id);

    void update(DeptEntity entity);

}
