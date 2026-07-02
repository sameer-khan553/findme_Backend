package findme_backend.sighting;

import java.util.List;

public interface SightingService {

    SightingResponse createSighting(Long missingPersonId,
                                    CreateSightingRequest request);

    List<SightingResponse> getSightingsByMissingPerson(Long missingPersonId);

    SightingResponse getSightingById(Long id);

    SightingResponse updateSighting(Long id,
                                    CreateSightingRequest request);

    void deleteSighting(Long id);
}
