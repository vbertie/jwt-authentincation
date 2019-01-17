package com.authentication.authenticationserver.authentication.dto;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
}