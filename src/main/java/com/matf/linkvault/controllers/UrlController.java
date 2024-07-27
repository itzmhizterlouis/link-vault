package com.matf.linkvault.controllers;


import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Folder;
import com.matf.linkvault.models.entities.Url;
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

    @Operation(summary = "Save a List of Urls, Don't add the folder name to the request for folderless urls")
    @PostMapping
    public GenericResponse saveUrls(@RequestBody List<UrlRequest> urlRequests) throws UserNotFoundException {

        return urlService.saveUrl(urlRequests);
    }

    @Operation(summary = "Get list of urls")
    @GetMapping
    public List<Url> getByNameParam() throws UserNotFoundException {

        return urlService.getAllUrls();
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

    @Operation(summary = "Create folders, Folder Name = -1 for unnamed folders")
    @PostMapping("folders")
    public GenericResponse saveFolders(@RequestBody List<FolderRequest> requests) throws UserNotFoundException {

        return urlService.saveFolders(requests);
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

    @Operation(summary = "Delete Url")
    @DeleteMapping("{urlId}")
    public GenericResponse deleteUrl(@PathVariable int urlId) throws UserNotFoundException {

        return urlService.deleteUrl(urlId);
    }

    @Operation(summary = "Delete Folder")
    @DeleteMapping("folders/{folderId}")
    public GenericResponse deleteFolder(@PathVariable int folderId) throws UserNotFoundException {

        return urlService.deleteFolder(folderId);
    }
}
