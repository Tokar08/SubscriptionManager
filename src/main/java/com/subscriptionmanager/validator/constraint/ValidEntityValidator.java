package com.subscriptionmanager.validator.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEntityValidator implements ConstraintValidator<ValidEntity, Object> {
    @Override
    public void initialize(ValidEntity constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null;
    }
}
