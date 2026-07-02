package findme_backend.missingperson.service;

import findme_backend.missingperson.dto.PhotoResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    PhotoResponse uploadPhoto(Long missingPersonId,
                              MultipartFile file);

    Resource getPhoto(Long photoId);

    List<PhotoResponse> getPhotosByMissingPerson(Long missingPersonId);
}