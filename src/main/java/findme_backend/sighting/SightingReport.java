package findme_backend.sighting;

import findme_backend.missingperson.entity.MissingPerson;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sighting_reports")
public class SightingReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime reportedAt;

    private String reporterName;

    private String reporterPhone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SightingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missing_person_id", nullable = false)
    private MissingPerson missingPerson;
}
