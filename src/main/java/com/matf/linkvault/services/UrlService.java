package com.matf.linkvault.services;


import com.matf.linkvault.exceptions.FolderNotFoundException;
import com.matf.linkvault.exceptions.UrlNotFoundException;
import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Folder;
import com.matf.linkvault.models.entities.Url;
import com.matf.linkvault.models.entities.UrlNameParam;
import com.matf.linkvault.models.requests.FolderRequest;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.repositories.FolderRepository;
import com.matf.linkvault.repositories.UrlRepository;
import com.matf.linkvault.utils.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final FolderRepository folderRepository;

    public GenericResponse saveUrl(UrlRequest urlRequest) throws UserNotFoundException {

        Url url = Url.builder()
                .url(urlRequest.getUrl())
                .name(urlRequest.getName())
                .userId(UserUtil.getLoggedInUser().get().getUserId())
                .tags(urlRequest.getTags().stream().map(String::toUpperCase).toList())
                .build();

        createFolderIfFolderDoesntExist(urlRequest, url);

        urlRepository.save(url);

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

    public List<Url> findAllByFolder(int folderId) throws UserNotFoundException {

        return urlRepository.findAllByFolderIdAndUserId(
                folderId, UserUtil.getLoggedInUser().get().getUserId());
    }

    public List<Folder> findAllFolders() throws UserNotFoundException {

        return folderRepository.findAllByUserId(UserUtil.getLoggedInUser().get().getUserId());
    }

    public Folder createFolder(FolderRequest request) throws UserNotFoundException {

        return folderRepository.save(
                Folder.builder()
                        .folderName(request.getFolderName())
                        .userId(UserUtil.getLoggedInUser().get().getUserId())
                        .build()
        );
    }

    public GenericResponse editUrl(UrlRequest request, int urlId) throws UserNotFoundException {

        Url url = urlRepository.findByUrlId(urlId).orElseThrow(UrlNotFoundException::new);

        url.setUrl(request.getUrl());
        url.setName(request.getName());
        url.setTags(request.getTags());

        createFolderIfFolderDoesntExist(request, url);

        urlRepository.save(url);

        return GenericResponse.builder()
                .message("Successfully edited url").build();
    }

    private void createFolderIfFolderDoesntExist(UrlRequest request, Url url) throws UserNotFoundException {
        if (request.getFolderName() != null) {

            if (folderRepository.existsByFolderNameAndUserId(request.getFolderName(), UserUtil.getLoggedInUser().get().getUserId())){

                url.setFolderId(folderRepository.findByFolderNameAndUserId(request.getFolderName(), UserUtil.getLoggedInUser().get().getUserId()).get().getFolderId());
            }
            else {

                int folderId = folderRepository.save(Folder.builder()
                        .folderName(request.getFolderName())
                        .userId(UserUtil.getLoggedInUser().get().getUserId())
                        .build()
                ).getFolderId();
                url.setFolderId(folderId);
            }
        }
    }

    public GenericResponse editFolder(FolderRequest request, int folderId) throws UserNotFoundException {

        Folder folder = folderRepository.findByFolderIdAndUserId(folderId, UserUtil.getLoggedInUser().get().getUserId()).orElseThrow(FolderNotFoundException::new);

        folder.setFolderName(request.getFolderName());

        folderRepository.save(folder);

        return GenericResponse.builder()
                .message("Folder has been successfully updated").build();
    }

    @Transactional
    public GenericResponse deleteUrl(int urlId) throws UserNotFoundException {

        Url url = urlRepository.findByUrlIdAndUserId(urlId, UserUtil.getLoggedInUser().get().getUserId()).orElseThrow(UserNotFoundException::new);

        urlRepository.delete(url);

        return GenericResponse.builder()
                .message("Url has been successfully deleted").build();
    }
}
