package com.subscriptionmanager.validator.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidEntityValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidEntity {
    String message() default "Entity is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
