package findme_backend.sighting;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SightingController {

    private final SightingService sightingService;

    public SightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    // Create a sighting report
    @PostMapping("/missing-persons/{missingPersonId}/sightings")
    public SightingResponse createSighting(
            @PathVariable Long missingPersonId,
            @Valid @RequestBody CreateSightingRequest request) {

        return sightingService.createSighting(missingPersonId, request);
    }

    // Get all sightings for a missing person
    @GetMapping("/missing-persons/{missingPersonId}/sightings")
    public List<SightingResponse> getSightingsByMissingPerson(
            @PathVariable Long missingPersonId) {

        return sightingService.getSightingsByMissingPerson(missingPersonId);
    }

    // Get one sighting
    @GetMapping("/sightings/{id}")
    public SightingResponse getSightingById(@PathVariable Long id) {

        return sightingService.getSightingById(id);
    }

    // Update a sighting
    @PutMapping("/sightings/{id}")
    public SightingResponse updateSighting(
            @PathVariable Long id,
            @Valid @RequestBody CreateSightingRequest request) {

        return sightingService.updateSighting(id, request);
    }

    // Delete a sighting
    @DeleteMapping("/sightings/{id}")
    public void deleteSighting(@PathVariable Long id) {

        sightingService.deleteSighting(id);
    }
}
