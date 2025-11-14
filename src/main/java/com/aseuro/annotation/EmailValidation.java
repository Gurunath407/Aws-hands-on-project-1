package com.aseuro.annotation;

import com.aseuro.aspects.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidation {

    String message() default "Invalid Email ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
