package findme_backend.caseUpdate;
import findme_backend.exception.ResourceNotFoundException;
import findme_backend.missingperson.entity.MissingPerson;
import findme_backend.missingperson.repository.MissingPersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseUpdateServiceImpl implements CaseUpdateService {

    private final CaseUpdateRepository caseUpdateRepository;
    private final MissingPersonRepository missingPersonRepository;

    public CaseUpdateServiceImpl(
            CaseUpdateRepository caseUpdateRepository,
            MissingPersonRepository missingPersonRepository) {

        this.caseUpdateRepository = caseUpdateRepository;
        this.missingPersonRepository = missingPersonRepository;
    }

    @Override
    public CaseUpdateResponse createUpdate(
            Long missingPersonId,
            CreateCaseUpdateRequest request) {

        MissingPerson missingPerson = missingPersonRepository.findById(missingPersonId)
                .orElseThrow(() -> new ResourceNotFoundException("Missing person not found"));

        CaseUpdate update = new CaseUpdate();

        update.setMessage(request.getMessage());
        update.setCreatedBy(request.getCreatedBy());
        update.setCreatedAt(LocalDateTime.now());
        update.setMissingPerson(missingPerson);

        CaseUpdate saved = caseUpdateRepository.save(update);

        return mapToResponse(saved);
    }

    @Override
    public List<CaseUpdateResponse> getUpdates(Long missingPersonId) {

        return caseUpdateRepository
                .findByMissingPersonIdOrderByCreatedAtDesc(missingPersonId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteUpdate(Long id) {

        if (!caseUpdateRepository.existsById(id)) {
            throw new ResourceNotFoundException("Case update not found");
        }

        caseUpdateRepository.deleteById(id);
    }

    private CaseUpdateResponse mapToResponse(CaseUpdate update) {

        return new CaseUpdateResponse(
                update.getId(),
                update.getMessage(),
                update.getCreatedBy(),
                update.getCreatedAt(),
                update.getMissingPerson().getId()
        );
    }
}
