package findme_backend.missingperson.dto;

import lombok.Data;
import java.time.LocalDate;

import findme_backend.missingperson.enums.Gender;
import jakarta.validation.constraints.*;

@Data
public class CreateMissingPersonRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age cannot be greater than 150")
    private Integer age;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Last seen location is required")
    private String lastSeenLocation;

    @NotNull(message = "Last seen date is required")
    private LocalDate lastSeenDate;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    private String bloodGroup;
    private Double height;
    private Double weight;
    private String identificationMarks;
    private String description;
}

