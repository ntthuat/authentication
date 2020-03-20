package com.ntthuat.auth.repository;

import com.ntthuat.auth.entity.Role;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Optional;

import static com.ntthuat.auth.constant.RoleName.ADMIN;
import static com.ntthuat.auth.constant.RoleName.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ntthuat
 */
@RunWith(SpringRunner.class)
@DataJpaTest
class RoleRepositoryTest {

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
