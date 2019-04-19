package com.winjean.repository;

import com.winjean.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    int deleteByIdIn(List<Long> ids);
}
