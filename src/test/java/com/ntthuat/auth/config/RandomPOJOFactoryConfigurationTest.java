package com.ntthuat.auth.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import uk.co.jemos.podam.api.PodamFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig({RandomPOJOFactoryConfiguration.class})
@DisplayName("Random POJO factory configuration")
class RandomPOJOFactoryConfigurationTest {

    @Autowired
    PodamFactory factory;

    @Test
    @DisplayName("factory bean is provided if Podam available")
    void testPodamFactoryProvided() {
        assertNotNull(factory);
    }
}