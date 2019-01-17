package com.authentication.authenticationserver.authentication;

import com.authentication.authenticationserver.authentication.dto.JwtResponse;
import com.authentication.authenticationserver.authentication.dto.SignInDto;
import com.authentication.authenticationserver.authentication.dto.SignUpDto;
import com.authentication.authenticationserver.infrastracture.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFacade {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User createUser(SignUpDto signUpDto) {

        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .username(signUpDto.getUsername())
                .build();

        user.setRoles(processRoles(signUpDto.getRoles()));

        return userRepository.save(user);
    }

    public JwtResponse processLogIn(SignInDto signInDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getUsername(),
                        signInDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtResponse(jwtProvider.generateJwtToken(authentication));
    }

    @Transactional
    public boolean isUsernameInUse(String value) {
        return userRepository.findByUsername(value).isPresent();
    }

    @Transactional
    public boolean isEmailInUse(String value) {
        return userRepository.findUserByEmail(value).isPresent();
    }

    private Set<Role> processRoles(Set<String> givenRoles) {

        Set<Role> roles = new HashSet<>();

        givenRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

                    roles.add(adminRole);
                    break;

                case "pm":
                    Role pmRole = roleRepository.findByName(Role.RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

                    roles.add(pmRole);
                    break;

                default:
                    Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        return roles;
    }

}

