package findme_backend.caseUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CaseUpdateResponse {

    private Long id;

    private String message;

    private String createdBy;

    private LocalDateTime createdAt;

    private Long missingPersonId;
}
