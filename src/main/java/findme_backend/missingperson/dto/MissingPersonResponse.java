package findme_backend.missingperson.dto;

import findme_backend.missingperson.enums.Gender;
import findme_backend.missingperson.enums.MissingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MissingPersonResponse {

    private Long id;

    private String caseNumber;

    private String fullName;

    private Integer age;

@NotNull
    private Gender gender;

    private String city;

    private String state;

    private MissingStatus status;

    private String photoUrl;
}