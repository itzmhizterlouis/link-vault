package com.matf.linkvault.repositories;


import com.matf.linkvault.models.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    Optional<Folder> findByFolderNameAndUserId(String folderName, int userId);
    boolean existsByFolderNameAndUserId(String folderName, int userId);

    List<Folder> findAllByUserId(int userId);

    Optional<Folder> findByFolderIdAndUserId(int folderId, int userId);
}
