package com.procedure.scheduling.controller;

import com.procedure.scheduling.dto.JsonResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.procedure.scheduling.dto.JsonResult.ErrorCode.VALIDATION_ERROR;

public abstract class BaseController {

    protected <T> JsonResult<T> setValidationErrors(BindingResult binding) {

        JsonResult<T> result = new JsonResult<>(VALIDATION_ERROR);
        for (FieldError fieldError : binding.getFieldErrors()) {
            result.getValidatorErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return result;
    }
}
