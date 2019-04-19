package com.winjean.repository;

import com.winjean.model.entity.ResourceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    ResourceEntity findResourceById(Long id);

    ResourceEntity findResourceByName(String name);

    ResourceEntity findResourceByParentId(String name);

    Long countByParentId(Long parentId);

    @Query(value = "select r.* from t_resource r " +
            "INNER JOIN t_role_resource rr on r.id =rr.resource_id " +
            "where rr.role_id = ?1",
            countQuery = "select count(*) from t_resource r " +
                    "INNER JOIN t_role_resource rr on r.id =rr.resource_id " +
                    "where rr.role_id = ?1",
            nativeQuery = true)
    Page<ResourceEntity> findResourcesByRole(Long roleId, Pageable page);

    @Query(value = "select r.* from t_resource r " +
            "INNER JOIN t_role_resource rr on r.id =rr.resource_id " +
            "INNER JOIN t_user_role ur on ur.role_id = rr.role_id " +
            "where ur.role_id = ?1",
            countQuery = "select count(*) from t_resource " +
                    "INNER JOIN t_role_resource rr on r.id =rr.resource_id " +
                    "INNER JOIN t_user_role ur on ur.role_id = rr.role_id " +
                    "where ur.role_id = ?1",
            nativeQuery = true)
    Page<ResourceEntity> findResourcesByUser(Long userId, Pageable page);

}
