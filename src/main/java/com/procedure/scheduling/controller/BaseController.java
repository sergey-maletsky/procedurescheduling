package com.procedure.scheduling.controller;

import com.procedure.scheduling.dto.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.procedure.scheduling.dto.JsonResult.ErrorCode.NO_ERROR;
import static com.procedure.scheduling.dto.JsonResult.ErrorCode.VALIDATION_ERROR;

public abstract class BaseController {

    protected interface BaseSaver<T> {
        T save(T t);
    }

    protected <T> ResponseEntity<JsonResult> createResponse(T study, BindingResult bindingResult, BaseSaver<T> saver) {

        JsonResult result;
        ResponseEntity<JsonResult> responseEntity;
        if (bindingResult.hasErrors()) {
            result = setValidationErrors(bindingResult);
            responseEntity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            T newStudy = saver.save(study);
            result = new JsonResult<>(NO_ERROR);
            result.setResult(newStudy);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return responseEntity;
    }

    protected <T> JsonResult<T> setValidationErrors(BindingResult binding) {

        JsonResult<T> result = new JsonResult<>(VALIDATION_ERROR);
        for (FieldError fieldError : binding.getFieldErrors()) {
            result.getValidatorErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return result;
    }
}
