package com.matf.linkvault.controllers;


import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Folder;
import com.matf.linkvault.models.entities.Url;
import com.matf.linkvault.models.entities.UrlNameParam;
import com.matf.linkvault.models.requests.FolderRequest;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("urls")
@RestController
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public GenericResponse saveUrl(@RequestBody UrlRequest urlRequest) throws UserNotFoundException {

        return urlService.saveUrl(urlRequest);
    }

    @GetMapping("{param}")
    public List<Url> unnamed(@PathVariable UrlNameParam param) throws UserNotFoundException {

        return urlService.findAllBasedOnParam(param);
    }

    @GetMapping("folders/{folderId}/all-urls")
    public List<Url> findAllByFolder(@PathVariable int folderId) throws UserNotFoundException {

        return urlService.findAllByFolder(folderId);
    }

    @GetMapping("folders")
    public List<Folder> findAllFolders() throws UserNotFoundException {

        return urlService.findAllFolders();
    }

    @PostMapping("folders")
    public Folder createFolder(@RequestBody FolderRequest request) throws UserNotFoundException {

        return urlService.createFolder(request);
    }

    @PutMapping("{urlId}")
    public GenericResponse editUrl(@RequestBody UrlRequest request, @PathVariable int urlId) throws UserNotFoundException {

        return urlService.editUrl(request, urlId);
    }
}
