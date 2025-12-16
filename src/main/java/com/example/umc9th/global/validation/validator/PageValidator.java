package com.example.umc9th.global.validation.validator;

import com.example.umc9th.global.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        boolean isValid = page != null && page >= 1;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "PAGE_INVALID")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
