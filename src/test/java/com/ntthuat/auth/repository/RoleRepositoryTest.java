package com.ntthuat.auth.repository;

import com.ntthuat.auth.AuthenticationApplication;
import com.ntthuat.auth.constant.RoleName;
import com.ntthuat.auth.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.inject.Inject;
import java.util.Optional;

import static com.ntthuat.auth.constant.RoleName.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ntthuat
 */
@SpringBootTest(classes = AuthenticationApplication.class)
@WithMockUser("admin")
class RoleRepositoryTest {

    @Inject
    RoleRepository roleRepository;

    @BeforeEach
    void setup() {
        roleRepository.deleteAll();
    }

    @Test
    void testSaveRole() {
        assertEquals(0, roleRepository.count());

        Role role = new Role();
        role.setRoleName(USER);
        roleRepository.save(role, true);

        assertEquals(1, roleRepository.count());
    }

    @Test
    void testFindByRoleName() {
        Role role = new Role();
        role.setRoleName(USER);
        roleRepository.save(role, true);
        assertEquals(1, roleRepository.count());

        Optional<Role> optRole = roleRepository.findByRoleName(USER);
        assertTrue(optRole.isPresent());
    }

}
