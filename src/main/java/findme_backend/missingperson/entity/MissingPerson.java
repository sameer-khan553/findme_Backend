package findme_backend.missingperson.entity;


import findme_backend.missingperson.enums.Gender;
import findme_backend.missingperson.enums.MissingStatus;
import findme_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missing_persons")
@Data
public class MissingPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String caseNumber;

    @Column(nullable = false)
    private String fullName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String bloodGroup;

    private Double height;

    private Double weight;

    private String identificationMarks;

    private LocalDate lastSeenDate;

    private String lastSeenLocation;

    private String city;

    private String state;

    private String country;

    @Column(length = 2000)
    private String description;

    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private MissingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by")
    private User reportedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy = "missingPerson",
        cascade = CascadeType.ALL,
        orphanRemoval = true
)
private List<MissingPersonPhoto> photos = new ArrayList<>();
}
