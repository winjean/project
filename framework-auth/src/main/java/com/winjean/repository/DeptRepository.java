package com.winjean.repository;

import com.winjean.model.entity.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface DeptRepository extends JpaRepository<DeptEntity, Long> {

    DeptEntity findDeptById(Long id);

    DeptEntity findDeptByName(String name);

    DeptEntity findByParentId(String name);

    Long countByParentId(Long parentId);

}
