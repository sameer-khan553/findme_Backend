package findme_backend.caseUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseUpdateRepository
        extends JpaRepository<CaseUpdate, Long> {

    List<CaseUpdate> findByMissingPersonIdOrderByCreatedAtDesc(Long missingPersonId);

}
