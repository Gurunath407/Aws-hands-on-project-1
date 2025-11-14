package com.aseuro.annotation;

import com.aseuro.aspects.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {

    String message() default "Invalid Password: must contain at least one uppercase letter, one lowercase letter, " +
            "one number, and be at least 6 characters long";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
