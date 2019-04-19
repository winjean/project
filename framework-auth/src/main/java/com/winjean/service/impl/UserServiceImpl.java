package com.winjean.service.impl;

import com.winjean.model.entity.UserEntity;
import com.winjean.repository.UserRepository;
import com.winjean.service.UserService;
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
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(UserEntity entity) {
        UserEntity module = userRepository.save(entity);
        log.info("module saved id = {}",module.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<UserEntity> optional =  userRepository.findById(id);
        if (optional.isPresent()){
            UserEntity module =  optional.get();
            userRepository.delete(module);
            log.info("module deleted id = {}",id);
        }else{
            log.info("no module exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void update(UserEntity entity) {
        Long id = entity.getId();
        Optional<UserEntity> optional =  userRepository.findById(id);
        if(optional.isPresent()){
            UserEntity module = userRepository.findById(id).get();
            module.setName(entity.getName());
            log.info("module updated id = {},name = {}", id, module.getName());
        }else{
            log.info("no module exist, id = {}", id);
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
}
