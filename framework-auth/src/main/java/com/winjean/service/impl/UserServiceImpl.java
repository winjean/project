package com.winjean.service.impl;

import com.winjean.model.entity.UserEntity;
import com.winjean.model.entity.UserRoleEntity;
import com.winjean.repository.UserRepository;
import com.winjean.repository.UserRoleRepository;
import com.winjean.service.UserService;
import com.winjean.utils.BeanUtils;
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
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(UserEntity entity) {
        BeanUtils.appendEntityCreateInfo(entity, "winjean");
        UserEntity user = userRepository.save(entity);
        log.info("user saved id = {}",user.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<UserEntity> optional =  userRepository.findById(id);
        if (optional.isPresent()){
            UserEntity user =  optional.get();
            userRepository.delete(user);
            log.info("user deleted id = {}",id);
        }else{
            log.info("no user exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void update(UserEntity entity) {
        Long id = entity.getId();
        Optional<UserEntity> optional =  userRepository.findById(id);
        if(optional.isPresent()){
            UserEntity user = userRepository.findById(id).get();
            user.setName(entity.getName());
            BeanUtils.updateEntityUpdateInfo(user, "winjean");
            userRepository.save(user);
            log.info("user updated id = {},name = {}", id, user.getName());
        }else{
            log.info("no user exist, id = {}", id);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> optional =  userRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public UserEntity findByName(String name) {
        return userRepository.findUserEntitiesByName(name);
    }

    @Override
    public Page<UserEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return userRepository.findAll(page);
    }

    @Override
    @Transactional
    public void save(List<UserRoleEntity> entities) {
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
    public void deleteUserRole(List<Long> ids) {
        userRoleRepository.deleteByIdIn(ids);
        log.info("delete RoleResource id = {}",ids.toArray());
    }
}
