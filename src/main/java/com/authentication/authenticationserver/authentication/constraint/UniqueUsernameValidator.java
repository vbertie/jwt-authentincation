package com.authentication.authenticationserver.authentication.constraint;

import com.authentication.authenticationserver.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final AuthenticationFacade authenticationFacade;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && !authenticationFacade.isUsernameInUse(value);
    }
}
