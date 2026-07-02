package findme_backend.dashboard;

import findme_backend.caseUpdate.CaseUpdateRepository;
import findme_backend.missingperson.enums.MissingStatus;
import findme_backend.missingperson.repository.MissingPersonPhotoRepository;
import findme_backend.missingperson.repository.MissingPersonRepository;
import findme_backend.sighting.SightingReportRepository;
import findme_backend.user.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final MissingPersonRepository missingPersonRepository;
    private final MissingPersonPhotoRepository photoRepository;
    private final SightingReportRepository sightingRepository;
    private final CaseUpdateRepository caseUpdateRepository;

    public DashboardServiceImpl(
            UserRepository userRepository,
            MissingPersonRepository missingPersonRepository,
            MissingPersonPhotoRepository photoRepository,
            SightingReportRepository sightingRepository,
            CaseUpdateRepository caseUpdateRepository) {

        this.userRepository = userRepository;
        this.missingPersonRepository = missingPersonRepository;
        this.photoRepository = photoRepository;
        this.sightingRepository = sightingRepository;
        this.caseUpdateRepository = caseUpdateRepository;
    }

    @Override
    public DashboardSummaryResponse getSummary() {

        long totalUsers = userRepository.count();

        long totalMissingPersons = missingPersonRepository.count();

        long missingPersons =
                missingPersonRepository.countByStatus(MissingStatus.MISSING);

        long foundPersons =
                missingPersonRepository.countByStatus(MissingStatus.FOUND);

        long totalPhotos = photoRepository.count();

        long totalSightings = sightingRepository.count();

        long totalCaseUpdates = caseUpdateRepository.count();

        return new DashboardSummaryResponse(
                totalUsers,
                totalMissingPersons,
                missingPersons,
                foundPersons,
                totalSightings,
                totalCaseUpdates,
                totalPhotos
        );
    }


    @Override
public List<CityStatisticsResponse> getTopCities() {
    return missingPersonRepository.getTopCities();
}
}
