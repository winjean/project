package com.winjean.service.impl;

import com.winjean.model.entity.ResourceEntity;
import com.winjean.model.entity.RoleEntity;
import com.winjean.model.entity.RoleResourceEntity;
import com.winjean.repository.RoleRepository;
import com.winjean.repository.RoleResourceRepository;
import com.winjean.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(RoleEntity entity) {
        RoleEntity role = roleRepository.save(entity);
        log.info("role saved id = {}",role.getId());
    }

    @Override
    @Transactional
    public void save(List<RoleResourceEntity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            entityManager.persist(entities.get(i));
            if (i % 100 == 0) { //一次一百条插入
                entityManager.flush();
                entityManager.clear();
            }
        }
        log.info(" batch save to DB success,list is {}",entities.toString());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<RoleEntity> optional =  roleRepository.findById(id);
        if (optional.isPresent()){
            RoleEntity role =  optional.get();
            roleRepository.delete(role);
            log.info("role deleted id = {}",id);
        }else{
            log.info("no role exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void deleteRoleResource(List<Long> ids) {
        roleResourceRepository.deleteByIdIn(ids);
        log.info("delete RoleResource id = {}",ids.toArray());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void update(RoleEntity entity) {
        Long id = entity.getId();
        Optional<RoleEntity> optional =  roleRepository.findById(id);
        if(optional.isPresent()){
            RoleEntity role = roleRepository.findById(id).get();
            role.setName(entity.getName());
            log.info("role updated id = {},name = {}", id, role.getName());
        }else{
            log.info("no role exist, id = {}", id);
        }
    }

    @Override
    public RoleEntity findById(Long id) {
        Optional<RoleEntity> optional =  roleRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public RoleEntity findByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public Page<RoleEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return roleRepository.findAll(page);
    }


}
