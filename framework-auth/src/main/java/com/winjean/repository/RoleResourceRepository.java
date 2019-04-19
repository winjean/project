package com.winjean.repository;

import com.winjean.model.entity.RoleResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface RoleResourceRepository extends JpaRepository<RoleResourceEntity, Long> {

    @Query(value="delete from t_role_resource e where e.id in (:ids) ")
    int deleteByIds(@Param("ids") List<Long> ids);

}
