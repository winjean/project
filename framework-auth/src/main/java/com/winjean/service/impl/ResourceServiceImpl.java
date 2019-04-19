package com.winjean.service.impl;

import com.winjean.model.entity.ResourceEntity;
import com.winjean.repository.ResourceRepository;
import com.winjean.service.ResourceService;
import com.winjean.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        BeanUtils.appendEntityCreateInfo(entity,"winjean");

        ResourceEntity resource = resourceRepository.save(entity);
        Long parentId = resource.getParentId();
        if(! StringUtils.isEmpty(parentId)){
            ResourceEntity parent = resourceRepository.findResourceById(parentId);
            if(null != parent && parent.isLeaf()){
                parent.setLeaf(false);
                resourceRepository.save(parent);
                log.info(" set resource id = {}, leaf = {}",parent.getId(), parent.isLeaf());
            }
        }

        log.info("resource saved id = {}",resource.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<ResourceEntity> optional =  resourceRepository.findById(id);

        if (optional.isPresent()){
            ResourceEntity resource =  optional.get();
            Long pid = resource.getParentId();
            resourceRepository.delete(resource);

            Long count = resourceRepository.countByParentId(pid);
            if(count == 0){
                ResourceEntity parent = resourceRepository.findResourceById(pid);
                parent.setLeaf(true);
                resourceRepository.save(parent);
            }

            log.info("resource deleted id = {}",id);
        }else{
            log.info("no resource exist, id = {}",id);
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
        return resourceRepository.findResourceByName(name);
    }

    @Override
    public Page<ResourceEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return resourceRepository.findAll(page);
    }

    @Override
    public Page<ResourceEntity> findResourcesByRole(Long roleId, int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return resourceRepository.findResourcesByRole(roleId, page);
    }

    @Override
    public Page<ResourceEntity> findResourcesByUser(Long userId, int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return resourceRepository.findResourcesByUser(userId, page);
    }
}
