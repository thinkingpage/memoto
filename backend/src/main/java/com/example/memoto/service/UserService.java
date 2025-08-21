package com.example.memoto.service;

import com.example.memoto.model.User;
import com.example.memoto.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(Authentication auth) {
        Object principal = auth.getPrincipal();
        if (!(principal instanceof Jwt jwt)) {
            throw new IllegalArgumentException("No JWT token");
        }

        String userId = jwt.getSubject();
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");
        String firstName = jwt.getClaimAsString("given_name");
        String lastName = jwt.getClaimAsString("family_name");

        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setUsername(username);
                    existingUser.setEmail(email);
                    existingUser.setFirstName(firstName);
                    existingUser.setLastName(lastName);
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setId(userId);
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setFirstName(firstName);
                    newUser.setLastName(lastName);
                    return userRepository.save(newUser);
                });
    }
}

