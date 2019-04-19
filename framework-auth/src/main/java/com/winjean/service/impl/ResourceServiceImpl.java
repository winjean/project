package com.winjean.service.impl;

import com.winjean.model.entity.ResourceEntity;
import com.winjean.repository.ResourceRepository;
import com.winjean.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    @Transactional
    public void save(ResourceEntity entity) {
        ResourceEntity module = resourceRepository.save(entity);
        log.info("module saved id = {}",module.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<ResourceEntity> optional =  resourceRepository.findById(id);
        if (optional.isPresent()){
            ResourceEntity module =  optional.get();
            resourceRepository.delete(module);
            log.info("module deleted id = {}",id);
        }else{
            log.info("no module exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void update(ResourceEntity entity) {
        Long id = entity.getId();
        Optional<ResourceEntity> optional =  resourceRepository.findById(id);
        if(optional.isPresent()){
            ResourceEntity module = resourceRepository.findById(id).get();
            module.setName(entity.getName());
            log.info("module updated id = {},name = {}", id, module.getName());
        }else{
            log.info("no module exist, id = {}", id);
        }
    }

    @Override
    public ResourceEntity findById(Long id) {
        Optional<ResourceEntity> optional =  resourceRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public ResourceEntity findByName(String name) {
        return resourceRepository.findModuleByName(name);
    }

    @Override
    public Page<ResourceEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return resourceRepository.findAll(page);
    }
}
