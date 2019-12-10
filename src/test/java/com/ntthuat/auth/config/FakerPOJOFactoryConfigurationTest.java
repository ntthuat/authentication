package com.ntthuat.auth.config;

import com.ntthuat.auth.AuthenticationApplication;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import uk.co.jemos.podam.api.PodamFactory;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig({FakerPOJOFactoryConfiguration.class})
@DisplayName("Fake POJO factory configuration")
class FakerPOJOFactoryConfigurationTest {

    @Autowired
    PodamFactory factory;

    @Test
    @DisplayName("factory bean is provided if Podam available")
    void testPodamFactoryProvided() {
        assertNotNull(factory);
    }

    @Test
    @DisplayName("use Faker as strategy if available")
    void testFakerStrategy() {
        User user = factory.manufacturePojo(User.class);
        System.out.println(user);
        assertNotNull(user.firstName);
        assertNotNull(user.lastName);
        assertNotNull(user.emailAddress);
        assertNotNull(user.sku);
        assertNotNull(user.address);
        assertNotNull(user.postcode);
        assertNotNull(user.random);
        assertNotNull(user.date);
    }

    @Data
    class User {
        @Value("#{name.firstName}")
        String firstName;

        @Value("#{name.lastName}")
        String lastName;

        @Email String emailAddress;

        @Pattern(regexp = "\\d{11}")
        String sku;

        @Value("#{address.fullAddress}")
        String address;

        @Value("#{address.zipCode}")
        String postcode;

        @Column(length = 10, nullable = false)
        String random;

        @Column(nullable = false)
        ZonedDateTime date;
    }
}