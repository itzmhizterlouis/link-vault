package com.matf.linkvault.services;


import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Url;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.repositories.UrlRepository;
import com.matf.linkvault.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private final UserRepository userRepository;

//    public GenericResponse saveUrl(UrlRequest urlRequest, OAuth2User principal) throws UserNotFoundException {
//
//        throwErrorIfUserDoesntExist(principal);
//
//        urlRepository.save(Url.builder()
//                .url(urlRequest.getUrl())
//                .name(urlRequest.getName())
//                .folderName(urlRequest.getFolderName())
//                .userId(userRepository.findByEmail(principal.getAttribute("email")).orElseThrow(UserNotFoundException::new).getUserId())
//                .tags(urlRequest.getTags().stream().map(String::toUpperCase).toList())
//                .build()
//        );
//
//        return GenericResponse.builder()
//                .message("Url has been saved.")
//                .build();
//    }
//
//    private void throwErrorIfUserDoesntExist(OAuth2User principal) throws UserNotFoundException {
//
//        if (!userRepository.existsByEmail(principal.getAttribute("email"))){
//
//            throw new UserNotFoundException();
//        }
//    }
}
