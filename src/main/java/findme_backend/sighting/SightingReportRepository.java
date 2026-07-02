package findme_backend.sighting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightingReportRepository
        extends JpaRepository<SightingReport, Long> {

    List<SightingReport> findByMissingPersonId(Long missingPersonId);

}
