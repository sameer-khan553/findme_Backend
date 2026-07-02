package findme_backend.caseUpdate;

import java.util.List;

public interface CaseUpdateService {

    CaseUpdateResponse createUpdate(
            Long missingPersonId,
            CreateCaseUpdateRequest request);

    List<CaseUpdateResponse> getUpdates(
            Long missingPersonId);

    void deleteUpdate(Long id);
}
