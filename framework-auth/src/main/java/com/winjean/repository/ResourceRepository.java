package com.winjean.repository;

import com.winjean.model.entity.ResourceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    ResourceEntity findModuleByName(String name);

    @Query(value = "select * from t_module where name = ?1",nativeQuery = true)
    List<ResourceEntity> find_SQL_Entity(String name);

    @Query(value = "select * from t_module where name = ?1",
            countQuery = "select count(*) from t_module where name = ?1",
            nativeQuery = true)
    Page<ResourceEntity> find_SQL_Page(String name, Pageable page);

}
