package com.ntthuat.auth.validation;

import com.ntthuat.auth.dto.LoginRequest;

import javax.inject.Named;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ntthuat
 */
@Named
public class ValidCredentialsValidator implements ConstraintValidator<ValidCredentials, LoginRequest> {

    @Override
    public boolean isValid(LoginRequest value, ConstraintValidatorContext context) {
        return value.getUsername() != null && value.getPassword() != null;
    }
}
