package com.authentication.authenticationserver.api;

import com.authentication.authenticationserver.authentication.dto.exception.UserExceptionDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class AuthenticationHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Set<UserExceptionDto> processUserValidation(MethodArgumentNotValidException ex) {

        BindingResult bindings = ex.getBindingResult();

        return Collections.unmodifiableList(bindings.getFieldErrors())
                .stream()
                .map(error -> new UserExceptionDto(error.getField(), error.getCode(), error.getRejectedValue()))
                .collect(Collectors.toSet());

    }
}
