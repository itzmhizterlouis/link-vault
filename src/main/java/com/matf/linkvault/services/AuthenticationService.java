package com.matf.linkvault.services;

import com.matf.linkvault.config.JwtService;
import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Folder;
import com.matf.linkvault.models.entities.User;
import com.matf.linkvault.models.requests.LoginRequest;
import com.matf.linkvault.models.requests.RegisterRequest;
import com.matf.linkvault.models.responses.RegisterResponse;
import com.matf.linkvault.repositories.FolderRepository;
import com.matf.linkvault.repositories.UserRepository;
import com.matf.linkvault.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final FolderRepository folderRepository;


    public RegisterResponse registerUser(RegisterRequest request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        var jwtToken = jwtService.generateToken(user);

        userRepository.save(user);

        folderRepository.save(Folder.builder()
                .folderName("-1")
                .userId(user.getUserId())
                .build()
        );

        return RegisterResponse.builder()
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .token(jwtToken).build();
    }

    public RegisterResponse login(LoginRequest request) throws UserNotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = UserUtil.getLoggedInUser().orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return RegisterResponse.builder()
                .token(jwtToken)
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }
}
