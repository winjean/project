package com.winjean.service.impl;

import com.winjean.model.entity.RoleEntity;
import com.winjean.repository.RoleRepository;
import com.winjean.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void save(RoleEntity entity) {
        RoleEntity module = roleRepository.save(entity);
        log.info("module saved id = {}",module.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<RoleEntity> optional =  roleRepository.findById(id);
        if (optional.isPresent()){
            RoleEntity module =  optional.get();
            roleRepository.delete(module);
            log.info("module deleted id = {}",id);
        }else{
            log.info("no module exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void update(RoleEntity entity) {
        Long id = entity.getId();
        Optional<RoleEntity> optional =  roleRepository.findById(id);
        if(optional.isPresent()){
            RoleEntity module = roleRepository.findById(id).get();
            module.setName(entity.getName());
            log.info("module updated id = {},name = {}", id, module.getName());
        }else{
            log.info("no module exist, id = {}", id);
        }
    }

    @Override
    public RoleEntity findById(Long id) {
        Optional<RoleEntity> optional =  roleRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public RoleEntity findByName(String name) {
        return roleRepository.findModuleByName(name);
    }

    @Override
    public Page<RoleEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return roleRepository.findAll(page);
    }
}
