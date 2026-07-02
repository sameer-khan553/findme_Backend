package findme_backend.caseUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCaseUpdateRequest {

    @NotBlank
    private String message;

    @NotBlank
    private String createdBy;
}
