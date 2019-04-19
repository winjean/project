package com.winjean.repository;

import com.winjean.model.entity.RoleEntity;
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

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleByName(String name);

    @Query(value = "select r.* from t_role r " +
            "INNER JOIN t_user_role ur on ur.role_id = r.id " +
            "where ur.user_id = ?1",
            countQuery = "select count(*) from t_role " +
                    "INNER JOIN t_user_role ur on ur.role_id = r.id " +
                    "where ur.user_id = ?1",
            nativeQuery = true)
    Page<RoleEntity> findRoleByUser(Long userId, Pageable page);

}
