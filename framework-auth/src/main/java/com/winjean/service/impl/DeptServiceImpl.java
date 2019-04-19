package com.winjean.service.impl;

import com.winjean.model.entity.DeptEntity;
import com.winjean.repository.DeptRepository;
import com.winjean.service.DeptService;
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
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public void save(DeptEntity entity) {
        BeanUtils.appendEntityCreateInfo(entity,"winjean");

        DeptEntity dept = deptRepository.save(entity);
        Long parentId = dept.getParentId();
        if(! StringUtils.isEmpty(parentId)){
            DeptEntity parent = deptRepository.findDeptById(parentId);
            if(null != parent && parent.isLeaf()){
                parent.setLeaf(false);
                deptRepository.save(parent);
                log.info(" set dept id = {}, leaf = {}",parent.getId(), parent.isLeaf());
            }
        }

        log.info("dept saved id = {}",dept.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void delete(Long id) {
        Optional<DeptEntity> optional =  deptRepository.findById(id);

        if (optional.isPresent()){
            DeptEntity resource =  optional.get();
            Long pid = resource.getParentId();
            deptRepository.delete(resource);

            Long count = deptRepository.countByParentId(pid);
            if(count == 0){
                DeptEntity parent = deptRepository.findDeptById(pid);
                parent.setLeaf(true);
                deptRepository.save(parent);
            }

            log.info("dept deleted id = {}",id);
        }else{
            log.info("no dept exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    public void update(DeptEntity entity) {
        Long id = entity.getId();
        Optional<DeptEntity> optional =  deptRepository.findById(id);
        if(optional.isPresent()){
            DeptEntity dept = deptRepository.findById(id).get();
            dept.setName(entity.getName());
            BeanUtils.updateEntityUpdateInfo(dept,"winjean");
            deptRepository.save(dept);
            log.info("dept updated id = {},name = {}", id, entity.getName());
        }else{
            log.info("no dept exist, id = {}", id);
        }
    }

    @Override
    public DeptEntity findById(Long id) {
        Optional<DeptEntity> optional =  deptRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public DeptEntity findByName(String name) {
        return deptRepository.findDeptByName(name);
    }

    @Override
    public Page<DeptEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return deptRepository.findAll(page);
    }
}
