package com.authentication.authenticationserver.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleInitializer {

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository) {

        return args -> {

            Role role1 = new Role(1L, Role.RoleName.ROLE_USER);
            Role role2 = new Role(2L, Role.RoleName.ROLE_PM);
            Role role3 = new Role(3L, Role.RoleName.ROLE_ADMIN);

            roleRepository.saveAll(Arrays.asList(role1,role2,role3));
        };
    }
}
