package com.matf.linkvault.repositories;


import com.matf.linkvault.models.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findAllByNameNotNullAndUserId(int userId);
    List<Url> findAllByNameNullAndUserId(int userId);

    List<Url> findAllByFolderIdAndUserId(int folderId, int userId);

    Optional<Url> findByUrlId(int urlId);
}
