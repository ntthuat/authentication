package com.ntthuat.auth.repository;

import com.ntthuat.auth.AuthenticationApplication;
import com.ntthuat.auth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import uk.co.jemos.podam.api.PodamFactory;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ntthuat
 */
@SpringBootTest
@WithMockUser("admin")
class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Inject
    PodamFactory podamFactory;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void testSaveUser() {
        assertEquals(0, userRepository.count());

        User user = new User();
        user.setUserName("userName");
        user.setPassword("password");

        userRepository.save(user, true);

        assertEquals(1, userRepository.count());
    }

    @Test
    void testFindByUserName() {
        User user = podamFactory.manufacturePojo(User.class);
        user.setUserName("userName");
        user.setPassword("password");
        userRepository.save(user, true);
        assertEquals(1, userRepository.count());

        Optional<User> optUser = userRepository.findByUserName("userName");
        assertTrue(optUser.isPresent());
    }

    @Test
    void testExistsByUserName() {
        User user = new User();
        user.setUserName("userName");
        user.setPassword("password");
        userRepository.save(user, true);
        assertEquals(1, userRepository.count());

        Boolean existUser = userRepository.existsByUserName("userName");
        assertTrue(existUser);
    }
}
