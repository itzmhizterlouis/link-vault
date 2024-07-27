package com.matf.linkvault.services;


import com.matf.linkvault.exceptions.FolderNotFoundException;
import com.matf.linkvault.exceptions.UrlNotFoundException;
import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.entities.Folder;
import com.matf.linkvault.models.entities.Url;
import com.matf.linkvault.models.requests.FolderRequest;
import com.matf.linkvault.models.requests.UrlRequest;
import com.matf.linkvault.models.responses.GenericResponse;
import com.matf.linkvault.repositories.FolderRepository;
import com.matf.linkvault.repositories.UrlRepository;
import com.matf.linkvault.utils.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final FolderRepository folderRepository;

    public GenericResponse saveUrl(List<UrlRequest> urlRequests) throws UserNotFoundException {
        List<Url> urls = new ArrayList<>();

        Url url;

        for (UrlRequest urlRequest: urlRequests) {

            url = Url.builder()
                    .name(urlRequest.getName())
                    .tags(urlRequest.getTags())
                    .userId(UserUtil.getLoggedInUser().get().getUserId())
                    .url(urlRequest.getUrl())
                    .build();

            if (urlRequest.getFolderId() == 0) {

                url.setFolderId(folderRepository.findByFolderNameAndUserId("-1", url.getUserId()).get().getFolderId());
            }
            else {

                url.setFolderId(urlRequest.getFolderId());
            }

            urls.add(url);
        }

//        List<Url> urls = urlRequests.parallelStream().map(urlRequest -> {
//            try {
//                return urlRequest.toDb();
//            } catch (UserNotFoundException e) {
//                return null;
//            }
//        }).toList();

        urlRepository.saveAll(urls);

        return GenericResponse.builder()
                .message("Url has been saved.")
                .build();
    }

    public List<Url> getAllUrls() throws UserNotFoundException {

        return urlRepository.findAllByUserId(UserUtil.getLoggedInUser().get().getUserId());
    }

    public List<Url> findAllByFolder(int folderId) throws UserNotFoundException {

        return urlRepository.findAllByFolderIdAndUserId(
                folderId, UserUtil.getLoggedInUser().get().getUserId());
    }

    public List<Folder> findAllFolders() throws UserNotFoundException {

        return folderRepository.findAllByUserId(UserUtil.getLoggedInUser().get().getUserId());
    }

    public GenericResponse saveFolders(List<FolderRequest> requests) throws UserNotFoundException {

        List<Folder> folders = new ArrayList<>();

        for (FolderRequest folder: requests) {

            folders.add(Folder.builder()
                    .folderName(folder.getFolderName())
                            .userId(UserUtil.getLoggedInUser().get().getUserId())
                    .build()
            );
        }

//        List<Folder> folders = requests.parallelStream().map(request -> {
//            try {
//                return request.toDb();
//            } catch (UserNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }).toList();

        folderRepository.saveAll(folders);

        return GenericResponse.builder()
                .message("Folders have been successfully saved").build();
    }

    public GenericResponse editUrl(UrlRequest request, int urlId) throws UserNotFoundException {

        Url url = urlRepository.findByUrlId(urlId).orElseThrow(UrlNotFoundException::new);

        url.setUrl(request.getUrl());
        url.setName(request.getName());
        url.setTags(request.getTags());

//        createFolderIfFolderDoesntExist(request, url);

        urlRepository.save(url);

        return GenericResponse.builder()
                .message("Successfully edited url").build();
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

    @Transactional
    public GenericResponse deleteFolder(int folderId) throws UserNotFoundException {

        Folder folder = folderRepository.findByFolderIdAndUserId(folderId, UserUtil.getLoggedInUser().get().getUserId()).orElseThrow(FolderNotFoundException::new);

        urlRepository.deleteAllByFolderIdAndUserId(folderId, UserUtil.getLoggedInUser().get().getUserId());

        folderRepository.delete(folder);

        return GenericResponse.builder()
                .message("Folder with all links in folder has been deleted")
                .build();
    }
}
