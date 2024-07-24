package com.matf.linkvault.controllers;


import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Folder;
import com.matf.linkvault.models.entities.Url;
import com.matf.linkvault.models.entities.UrlNameParam;
import com.matf.linkvault.models.requests.FolderRequest;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.services.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Operation(summary = "Save Url can also be used for the endpoint to create folder where the user selects url to save")
    @PostMapping
    public GenericResponse saveUrl(@RequestBody UrlRequest urlRequest) throws UserNotFoundException {

        return urlService.saveUrl(urlRequest);
    }

    @Operation(summary = "Get named or unnamed links")
    @GetMapping("{param}")
    public List<Url> getByNameParam(@PathVariable UrlNameParam param) throws UserNotFoundException {

        return urlService.findAllBasedOnParam(param);
    }

    @Operation(summary = "Get all urls in a folder for a specific user")
    @GetMapping("folders/{folderId}/all-urls")
    public List<Url> findAllByFolder(@PathVariable int folderId) throws UserNotFoundException {

        return urlService.findAllByFolder(folderId);
    }

    @Operation(summary = "Finding all folders for specific user")
    @GetMapping("folders")
    public List<Folder> findAllFolders() throws UserNotFoundException {

        return urlService.findAllFolders();
    }

    @Operation(summary = "Create folder when there's no url provided")
    @PostMapping("folders")
    public Folder createFolder(@RequestBody FolderRequest request) throws UserNotFoundException {

        return urlService.createFolder(request);
    }

    @Operation(summary = "Edit Url can also be used for the edit folder where url is provided")
    @PutMapping("{urlId}")
    public GenericResponse editUrl(@RequestBody UrlRequest request, @PathVariable int urlId) throws UserNotFoundException {

        return urlService.editUrl(request, urlId);
    }

    @Operation(summary = "Edit Folder where no url details are provided")
    @PutMapping("folders/{folderId}")
    public GenericResponse editFolder(@RequestBody FolderRequest request, @PathVariable int folderId) throws UserNotFoundException {

        return urlService.editFolder(request, folderId);
    }

    @DeleteMapping("{urlId}")
    public GenericResponse deleteFolder(@PathVariable int urlId) throws UserNotFoundException {

        return urlService.deleteUrl(urlId);
    }
}
