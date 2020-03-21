package com.ntthuat.auth.repository;

import com.ntthuat.auth.entity.Role;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static com.ntthuat.auth.constant.RoleName.ADMIN;
import static com.ntthuat.auth.constant.RoleName.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ntthuat
 */
class RoleRepositoryTest extends RepositoryTestBase {

    @Inject
    RoleRepository roleRepository;

    @Test
    @Disabled
    void testSaveRole() {
        roleRepository.deleteAll();
        assertEquals(0, roleRepository.count());

        Role role = new Role();
        role.setRoleName(USER);
        roleRepository.save(role, true);

        assertEquals(1, roleRepository.count());
    }

    @Test
    void testFindByRoleName() {
        Optional<Role> optRole = roleRepository.findByRoleName(ADMIN);
        assertTrue(optRole.isPresent());
    }
}
