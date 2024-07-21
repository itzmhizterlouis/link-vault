package com.matf.linkvault.services;


import com.matf.linkvault.models.User;
import com.matf.linkvault.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public User registerUser(String email, String name) {

        return userRepository.save(User.builder()
                .email(email)
                .name(name.toUpperCase())
                .build());
    }
}
