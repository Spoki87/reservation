package com.reservation.config;

import com.reservation.user.appuser.model.Role;
import com.reservation.user.appuser.model.User;
import com.reservation.user.appuser.repository.UserRepository;
import com.reservation.user.securitry.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "admin@example.com";

            if (userRepository.findByEmail(email).isEmpty()) {
                User user = new User("SUPERADMIN","example@example.pl",passwordEncoder.bCryptPasswordEncoder().encode("root"), Role.SUPER_ADMIN);
                userRepository.save(user);
                System.out.println("✅ Admin added");
            } else {
                System.out.println("ℹ️ Admin exists");
            }
        };
    }
}
