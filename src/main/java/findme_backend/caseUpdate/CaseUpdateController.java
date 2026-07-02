package findme_backend.caseUpdate;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CaseUpdateController {

    private final CaseUpdateService caseUpdateService;

    public CaseUpdateController(CaseUpdateService caseUpdateService) {
        this.caseUpdateService = caseUpdateService;
    }

    @PostMapping("/missing-persons/{missingPersonId}/updates")
    public CaseUpdateResponse createUpdate(
            @PathVariable Long missingPersonId,
            @Valid @RequestBody CreateCaseUpdateRequest request) {

        return caseUpdateService.createUpdate(missingPersonId, request);
    }

    @GetMapping("/missing-persons/{missingPersonId}/updates")
    public List<CaseUpdateResponse> getUpdates(
            @PathVariable Long missingPersonId) {

        return caseUpdateService.getUpdates(missingPersonId);
    }

    @DeleteMapping("/updates/{id}")
    public void deleteUpdate(@PathVariable Long id) {

        caseUpdateService.deleteUpdate(id);
    }
}
