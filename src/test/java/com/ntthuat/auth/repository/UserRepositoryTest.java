package com.ntthuat.auth.repository;

import com.ntthuat.auth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ntthuat
 */
class UserRepositoryTest extends RepositoryTestBase {

    @Inject
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindByUserName_success() {
        User user = podamFactory.manufacturePojo(User.class);
        /*userRepository.save(user);

        System.out.println(userRepository.count());*/
    }
}
