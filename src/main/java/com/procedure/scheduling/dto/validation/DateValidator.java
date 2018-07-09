package com.procedure.scheduling.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Objects;

public class DateValidator implements ConstraintValidator<ValidDate, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {

        return Objects.nonNull(value);
    }

    @Override
    public void initialize(ValidDate constraintAnnotation) {}
}
