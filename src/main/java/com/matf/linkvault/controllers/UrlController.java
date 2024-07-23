package com.matf.linkvault.controllers;


import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("url/")
@RestController
public class UrlController {

    private final UrlService urlService;

//    @PostMapping
//    public GenericResponse saveUrl(@AuthenticationPrincipal OAuth2User principal, @RequestBody UrlRequest urlRequest) throws UserNotFoundException {
//
//        return urlService.saveUrl(urlRequest, principal);
//    }
}
