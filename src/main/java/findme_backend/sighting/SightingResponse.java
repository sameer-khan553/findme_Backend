package findme_backend.sighting;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SightingResponse {

    private Long id;

    private String description;

    private String city;

    private String location;

    private LocalDateTime reportedAt;

    private String reporterName;

    private String reporterPhone;

    private SightingStatus status;

    private Long missingPersonId;
}
