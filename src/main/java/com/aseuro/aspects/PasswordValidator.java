package com.aseuro.aspects;

import com.aseuro.annotation.PasswordValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    // One uppercase, one lowercase, no special chars, numbers optional, min 6 chars
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{6,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank())
            return false;

        return pattern.matcher(value).matches();
    }
}
