package com.ntthuat.auth.config;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.common.AttributeStrategy;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Podam POJO Factory configuration, with strategy using Faker.
 *
 * @author ntthuat
 * TODO: move it to common
 */
@Configuration
@ConditionalOnClass({PodamFactory.class, Faker.class})
public class FakerPOJOFactoryConfiguration {

    @Bean
    PodamFactory podamFactory(FakerStrategy fakerStrategy, JpaStrategy jpaStrategy) {
        PodamFactoryImpl factory = new PodamFactoryImpl();

        RandomDataProviderStrategy strategy = (RandomDataProviderStrategy) factory.getStrategy();
        strategy.addOrReplaceAttributeStrategy(Email.class, fakerStrategy);
        strategy.addOrReplaceAttributeStrategy(Pattern.class, fakerStrategy);
        strategy.addOrReplaceAttributeStrategy(Value.class, fakerStrategy);
        strategy.addOrReplaceAttributeStrategy(Column.class, jpaStrategy);

        return factory;
    }

    @Bean
    Faker faker() {
        return new Faker();
    }

    @Bean
    FakerStrategy fakerStrategy(Faker faker) {
        return new FakerStrategy(faker);
    }

    @Bean
    JpaStrategy jpaStrategy(Faker faker) {
        return new JpaStrategy(faker);
    }

    @AllArgsConstructor
    class FakerStrategy implements AttributeStrategy<String> {

        final Faker faker;

        @Override
        public String getValue(Class<?> type, List<Annotation> annotations) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().isAssignableFrom(Email.class)) {
                    return faker.internet().emailAddress();
                }
                if (annotation.annotationType().isAssignableFrom(Pattern.class)) {
                    return faker.regexify(((Pattern) annotation).regexp());
                }
                if (annotation.annotationType().isAssignableFrom(Value.class)) {
                    return faker.expression(((Value) annotation).value());
                }
                if (annotation.annotationType().isAssignableFrom(Value.class)) {
                    return faker.expression(((Value) annotation).value());
                }
            }
            return faker.lorem().sentence();
        }
    }

    @AllArgsConstructor
    class JpaStrategy implements AttributeStrategy<Object> {

        final Faker faker;

        @Override
        public Object getValue(Class<?> type, List<Annotation> annotations) {
            return annotations.stream()
                    .filter(annotation -> annotation.annotationType().isAssignableFrom(Column.class))
                    .findFirst()
                    .map(annotation -> {
                        Column column = (Column) annotation;

                        if (type.isAssignableFrom(Date.class)) {
                            return new Date();
                        }

                        if (type.isAssignableFrom(LocalDateTime.class)) {
                            return LocalDateTime.now();
                        }

                        if (type.isAssignableFrom(ZonedDateTime.class)) {
                            return ZonedDateTime.now(ZoneId.systemDefault());
                        }

                        if (type.isAssignableFrom(String.class)) {
                            if (column.length() != 0) {
                                return faker.lorem().characters(4, column.length());
                            }
                            return faker.lorem().sentence();
                        }

                        return null;
                    })
                    .orElse(null);
        }
    }
}
