package com.matf.linkvault.repositories;


import com.matf.linkvault.models.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findAllByUserId(int userId);

    List<Url> findAllByFolderIdAndUserId(int folderId, int userId);

    Optional<Url> findByUrlId(int urlId);

    Optional<Url> findByUrlIdAndUserId(int urlId, int userId);

    void deleteAllByFolderIdAndUserId(int folderId, int userId);
}
