package com.ntthuat.auth.repository;

import com.ntthuat.auth.constant.RoleName;
import com.ntthuat.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ntthuat
 */
@Repository
public interface RoleRepository extends RepositoryExtensions<Role, Long>, JpaSpecificationExecutor<Role> {

    Optional<Role> findByRoleName(RoleName roleName);
}
