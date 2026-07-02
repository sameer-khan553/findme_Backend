package findme_backend.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryResponse {

    private long totalUsers;

    private long totalMissingPersons;

    private long missingPersons;

    private long foundPersons;

    private long totalSightings;

    private long totalCaseUpdates;

    private long totalPhotos;
}
