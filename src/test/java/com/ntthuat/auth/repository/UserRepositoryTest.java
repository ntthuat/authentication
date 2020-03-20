package com.ntthuat.auth.repository;

import com.ntthuat.auth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ntthuat
 */
@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    /*@Inject
    PodamFactory podamFactory;*/

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void testSaveUser() {
        assertEquals(0, userRepository.count());

        User user = new User();
        // TODO: refactor here by PodamFactory
        user.setUserName("userName");
        user.setPassword("password");
        user.setRoles(null);
        user.setEmail("Email@gmail.com");
        ///////////////////////////////////////

        userRepository.save(user, true);

        assertEquals(1, userRepository.count());
    }

    @Test
    void testFindByUserName() {
        PodamFactory factory = new PodamFactoryImpl();
        User user = factory.manufacturePojo(User.class);
        // TODO: refactor here by PodamFactory
        user.setUserName("userName");
        user.setPassword("password");
        user.setRoles(null);
        user.setEmail("Email@gmail.com");
        ///////////////////////////////////////
        userRepository.save(user, true);
        assertEquals(1, userRepository.count());

        Optional<User> optUser = userRepository.findByUserName("userName");
        assertTrue(optUser.isPresent());
    }

    @Test
    void testExistsByUserName() {
        User user = new User();
        // TODO: refactor here by PodamFactory
        user.setUserName("userName");
        user.setPassword("password");
        user.setRoles(null);
        user.setEmail("Email@gmail.com");
        ///////////////////////////////////////
        userRepository.save(user, true);
        assertEquals(1, userRepository.count());

        Boolean existUser = userRepository.existsByUserName("userName");
        assertTrue(existUser);
    }

}
