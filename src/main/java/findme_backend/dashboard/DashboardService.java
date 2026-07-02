package findme_backend.dashboard;
import java.util.List;

public interface DashboardService {

    DashboardSummaryResponse getSummary();
    
        List<CityStatisticsResponse> getTopCities();
}
