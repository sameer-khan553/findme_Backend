package findme_backend.sighting;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSightingRequest {

    @NotBlank
    private String description;

    @NotBlank
    private String city;

    @NotBlank
    private String location;

    private String reporterName;

    private String reporterPhone;
}