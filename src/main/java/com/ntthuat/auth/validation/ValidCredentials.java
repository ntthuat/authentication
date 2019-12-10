package com.ntthuat.auth.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ntthuat
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidCredentialsValidator.class})
public @interface ValidCredentials {

    String message() default "refresh_token or username and password is required";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
