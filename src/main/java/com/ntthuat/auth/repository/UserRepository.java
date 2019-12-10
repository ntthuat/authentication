package com.ntthuat.auth.repository;

import com.ntthuat.auth.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ntthuat
 */
@Repository
public interface UserRepository extends RepositoryExtensions<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
