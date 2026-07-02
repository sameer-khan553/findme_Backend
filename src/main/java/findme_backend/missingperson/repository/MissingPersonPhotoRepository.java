package findme_backend.missingperson.repository;

import findme_backend.missingperson.entity.MissingPersonPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissingPersonPhotoRepository
        extends JpaRepository<MissingPersonPhoto, Long> {

    List<MissingPersonPhoto> findByMissingPersonId(Long missingPersonId);

}