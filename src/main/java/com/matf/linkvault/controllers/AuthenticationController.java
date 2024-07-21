package com.matf.linkvault.controllers;

import com.matf.linkvault.models.User;
import com.matf.linkvault.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("authentication")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("register")
    public User registerUser(@AuthenticationPrincipal OAuth2User principal) {

        return authenticationService.registerUser(principal.getAttribute("email"), principal.getAttribute("name"));
    }
}
