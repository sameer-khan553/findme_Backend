package findme_backend.missingperson.service.impl;

import findme_backend.exception.FileStorageException;
import findme_backend.missingperson.dto.PhotoResponse;
import findme_backend.missingperson.entity.MissingPerson;
import findme_backend.missingperson.entity.MissingPersonPhoto;
import findme_backend.missingperson.repository.MissingPersonPhotoRepository;
import findme_backend.missingperson.repository.MissingPersonRepository;
import findme_backend.missingperson.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final MissingPersonRepository missingPersonRepository;
    private final MissingPersonPhotoRepository photoRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public PhotoServiceImpl(MissingPersonRepository missingPersonRepository,
                            MissingPersonPhotoRepository photoRepository) {
        this.missingPersonRepository = missingPersonRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public PhotoResponse uploadPhoto(Long missingPersonId,
                                     MultipartFile file) {

        MissingPerson missingPerson = missingPersonRepository.findById(missingPersonId)
                .orElseThrow(() -> new FileStorageException("Missing person not found"));

        if (file.isEmpty()) {
            throw new FileStorageException("File is empty");
        }

        if (file.getContentType() == null ||
                !file.getContentType().startsWith("image/")) {
            throw new FileStorageException("Only image files are allowed");
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            throw new FileStorageException("Maximum file size is 5MB");
        }

        String extension =
                StringUtils.getFilenameExtension(file.getOriginalFilename());

        String storedFileName = (extension == null || extension.isBlank())
                ? UUID.randomUUID().toString()
                : UUID.randomUUID() + "." + extension;

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path destination = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(), destination);

            MissingPersonPhoto photo = new MissingPersonPhoto();

            photo.setOriginalFileName(file.getOriginalFilename());
            photo.setStoredFileName(storedFileName);
            photo.setFilePath(destination.toString());
            photo.setContentType(file.getContentType());
            photo.setFileSize(file.getSize());
            photo.setUploadedAt(LocalDateTime.now());
            photo.setMissingPerson(missingPerson);

            MissingPersonPhoto saved = photoRepository.save(photo);

            return new PhotoResponse(
                    saved.getId(),
                    saved.getOriginalFileName(),
                    saved.getContentType(),
                    saved.getFileSize(),
                    "/api/photos/" + saved.getId()
            );

        } catch (IOException e) {
            throw new FileStorageException("Failed to upload image", e);
        }
    }

    @Override
    public Resource getPhoto(Long photoId) {

        MissingPersonPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new FileStorageException("Photo not found"));

        try {

            Path path = Paths.get(photo.getFilePath());

            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                throw new FileStorageException("Image file not found");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new FileStorageException("Unable to load image", e);
        }
    }

    @Override
    public List<PhotoResponse> getPhotosByMissingPerson(Long missingPersonId) {

        return photoRepository.findByMissingPersonId(missingPersonId)
                .stream()
                .map(photo -> new PhotoResponse(
                        photo.getId(),
                        photo.getOriginalFileName(),
                        photo.getContentType(),
                        photo.getFileSize(),
                        "/api/photos/" + photo.getId()
                ))
                .toList();
    }
}