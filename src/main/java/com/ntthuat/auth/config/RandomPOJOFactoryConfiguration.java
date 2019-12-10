package com.ntthuat.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Podam POJO Factory configuration, with default strategy (Random)
 *
 * @author ntthuat
 */
@Configuration
@ConditionalOnClass(PodamFactory.class)
public class RandomPOJOFactoryConfiguration {

    @Bean
    @ConditionalOnMissingBean
    PodamFactory podamFactory() {
        return new PodamFactoryImpl();
    }
}
