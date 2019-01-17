package com.authentication.authenticationserver.authentication.dto.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserExceptionDto {

    private String field;
    private String code;
    private Object rejectedValue;
}