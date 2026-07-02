package findme_backend.dashboard;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityStatisticsResponse {

    private String city;

    private Long count;

}
