package com.matf.linkvault.controllers;

import com.matf.linkvault.exceptions.IncorrectEmailOrPasswordException;
import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.requests.LoginRequest;
import com.matf.linkvault.models.requests.RegisterRequest;
import com.matf.linkvault.models.responses.RegisterResponse;
import com.matf.linkvault.models.responses.SignUpResponse;
import com.matf.linkvault.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("authentication")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest request) {

        return authenticationService.registerUser(request);
    }

    @PostMapping("login")
    public RegisterResponse loginUser(@RequestBody LoginRequest request) throws UserNotFoundException {

        return authenticationService.login(request);
    }
}
