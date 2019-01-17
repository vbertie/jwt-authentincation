package com.authentication.authenticationserver.api;


import com.authentication.authenticationserver.authentication.AuthenticationFacade;
import com.authentication.authenticationserver.authentication.dto.JwtResponse;
import com.authentication.authenticationserver.authentication.dto.SignInDto;
import com.authentication.authenticationserver.authentication.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInDto signInDto) {

        JwtResponse jwtResponse = authenticationFacade.processLogIn(signInDto);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDto signUpDto) {

        authenticationFacade.createUser(signUpDto);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}