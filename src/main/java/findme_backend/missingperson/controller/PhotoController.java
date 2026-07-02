package findme_backend.missingperson.controller;

import findme_backend.missingperson.dto.PhotoResponse;
import findme_backend.missingperson.service.PhotoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    // Upload a photo
    @PostMapping("/missing-persons/{missingPersonId}/photos")
    public PhotoResponse uploadPhoto(
            @PathVariable Long missingPersonId,
            @RequestParam("file") MultipartFile file) {

        return photoService.uploadPhoto(missingPersonId, file);
    }

    // Get all photos of a missing person
    @GetMapping("/missing-persons/{missingPersonId}/photos")
    public List<PhotoResponse> getPhotos(
            @PathVariable Long missingPersonId) {

        return photoService.getPhotosByMissingPerson(missingPersonId);
    }

    // Display a photo
    @GetMapping("/photos/{photoId}")
    public ResponseEntity<Resource> getPhoto(
            @PathVariable Long photoId) {

        Resource resource = photoService.getPhoto(photoId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}