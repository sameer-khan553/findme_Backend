package findme_backend.sighting;
import findme_backend.exception.ResourceNotFoundException;
import findme_backend.missingperson.entity.MissingPerson;
import findme_backend.missingperson.repository.MissingPersonRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SightingServiceImpl implements SightingService {

    private final MissingPersonRepository missingPersonRepository;
    private final SightingReportRepository sightingRepository;

    public SightingServiceImpl(MissingPersonRepository missingPersonRepository,
                               SightingReportRepository sightingRepository) {
        this.missingPersonRepository = missingPersonRepository;
        this.sightingRepository = sightingRepository;
    }

    @Override
    public SightingResponse createSighting(Long missingPersonId,
                                           CreateSightingRequest request) {

        MissingPerson missingPerson = missingPersonRepository.findById(missingPersonId)
                .orElseThrow(() -> new ResourceNotFoundException("Missing person not found"));

        SightingReport report = new SightingReport();

        report.setDescription(request.getDescription());
        report.setCity(request.getCity());
        report.setLocation(request.getLocation());
        report.setReporterName(request.getReporterName());
        report.setReporterPhone(request.getReporterPhone());

        report.setReportedAt(LocalDateTime.now());
        report.setStatus(SightingStatus.PENDING);
        report.setMissingPerson(missingPerson);

        SightingReport saved = sightingRepository.save(report);

        return mapToResponse(saved);
    }

    @Override
    public List<SightingResponse> getSightingsByMissingPerson(Long missingPersonId) {

        return sightingRepository.findByMissingPersonId(missingPersonId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SightingResponse getSightingById(Long id) {

        SightingReport report = sightingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sighting not found"));

        return mapToResponse(report);
    }

    @Override
    public SightingResponse updateSighting(Long id,
                                           CreateSightingRequest request) {

        SightingReport report = sightingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sighting not found"));

        report.setDescription(request.getDescription());
        report.setCity(request.getCity());
        report.setLocation(request.getLocation());
        report.setReporterName(request.getReporterName());
        report.setReporterPhone(request.getReporterPhone());

        SightingReport updated = sightingRepository.save(report);

        return mapToResponse(updated);
    }

    @Override
    public void deleteSighting(Long id) {

        if (!sightingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sighting not found");
        }

        sightingRepository.deleteById(id);
    }

    private SightingResponse mapToResponse(SightingReport report) {

        return new SightingResponse(
                report.getId(),
                report.getDescription(),
                report.getCity(),
                report.getLocation(),
                report.getReportedAt(),
                report.getReporterName(),
                report.getReporterPhone(),
                report.getStatus(),
                report.getMissingPerson().getId()
        );
    }
}
