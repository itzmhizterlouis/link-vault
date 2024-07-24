package com.matf.linkvault.services;


import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Url;
import com.matf.linkvault.models.entities.UrlNameParam;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.repositories.UrlRepository;
import com.matf.linkvault.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public GenericResponse saveUrl(UrlRequest urlRequest) throws UserNotFoundException {

        urlRepository.save(Url.builder()
                .url(urlRequest.getUrl())
                .name(urlRequest.getName())
                .folderName(urlRequest.getFolderName())
                .userId(UserUtil.getLoggedInUser().get().getUserId())
                .tags(urlRequest.getTags().stream().map(String::toUpperCase).toList())
                .build()
        );

        return GenericResponse.builder()
                .message("Url has been saved.")
                .build();
    }

    public List<Url> findAllBasedOnParam(UrlNameParam param) throws UserNotFoundException {

        int userId = UserUtil.getLoggedInUser().get().getUserId();

        if (param.equals(UrlNameParam.NAMED)) {

            return urlRepository.findAllByNameNotNullAndUserId(userId);
        }

        return urlRepository.findAllByNameNullAndUserId(userId);
    }
}
