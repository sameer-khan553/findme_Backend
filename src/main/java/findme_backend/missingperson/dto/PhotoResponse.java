package findme_backend.missingperson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoResponse {

    private Long id;

    private String originalFileName;

    private String contentType;

    private Long fileSize;

    private String fileUrl;
}