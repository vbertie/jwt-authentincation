package com.authentication.authenticationserver.authentication.dto;

import com.authentication.authenticationserver.authentication.constraint.UniqueEmail;
import com.authentication.authenticationserver.authentication.constraint.UniqueUsername;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class SignUpDto {

    @NotBlank
    @Size(min = 3, max = 50)
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    @UniqueEmail
    private String email;
    
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}