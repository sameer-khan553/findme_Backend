package findme_backend.missingperson.repository;

import findme_backend.dashboard.CityStatisticsResponse;
import findme_backend.missingperson.entity.MissingPerson;
import findme_backend.missingperson.enums.MissingStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MissingPersonRepository
        extends JpaRepository<MissingPerson, Long>, JpaSpecificationExecutor<MissingPerson>  {

    Optional<MissingPerson> findByCaseNumber(String caseNumber);
     List<MissingPerson> findByFullNameContainingIgnoreCase(String fullName);

    List<MissingPerson> findByCityIgnoreCase(String city);

    List<MissingPerson> findByStatus(MissingStatus status);

    long countByStatus(MissingStatus status);


    @Query("""
SELECT new findme_backend.dashboard.CityStatisticsResponse(
m.city,
COUNT(m)
)
FROM MissingPerson m
GROUP BY m.city
ORDER BY COUNT(m) DESC
""")
List<CityStatisticsResponse> getTopCities();
}
