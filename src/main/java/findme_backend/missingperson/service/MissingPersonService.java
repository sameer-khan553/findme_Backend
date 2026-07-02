package findme_backend.missingperson.service;
import findme_backend.exception.ResourceNotFoundException;
import findme_backend.missingperson.dto.CreateMissingPersonRequest;
import findme_backend.missingperson.dto.MissingPersonResponse;
import findme_backend.missingperson.entity.MissingPerson;
import findme_backend.missingperson.enums.MissingStatus;
import findme_backend.missingperson.repository.MissingPersonRepository;
import findme_backend.missingperson.specification.MissingPersonSpecification;
import findme_backend.user.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service

public class MissingPersonService {

    private final MissingPersonRepository repository;

    public MissingPersonService(MissingPersonRepository repository) {
        this.repository = repository;
    }

    public MissingPersonResponse createMissingPerson(
            CreateMissingPersonRequest request,
            Authentication authentication) {

        User loggedInUser = (User) authentication.getPrincipal();

        MissingPerson person = new MissingPerson();

        person.setCaseNumber("FM-" + UUID.randomUUID().toString().substring(0, 8));
        person.setFullName(request.getFullName());
        person.setAge(request.getAge());
        person.setGender(request.getGender());
        person.setBloodGroup(request.getBloodGroup());
        person.setHeight(request.getHeight());
        person.setWeight(request.getWeight());
        person.setIdentificationMarks(request.getIdentificationMarks());
        person.setLastSeenDate(request.getLastSeenDate());
        person.setLastSeenLocation(request.getLastSeenLocation());
        person.setCity(request.getCity());
        person.setState(request.getState());
        person.setCountry(request.getCountry());
        person.setDescription(request.getDescription());

        person.setStatus(MissingStatus.MISSING);
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setReportedBy(loggedInUser);

        person = repository.save(person);

        MissingPersonResponse response = new MissingPersonResponse();
        response.setId(person.getId());
        response.setCaseNumber(person.getCaseNumber());
        response.setFullName(person.getFullName());
        response.setAge(person.getAge());
        response.setGender(person.getGender());
        response.setCity(person.getCity());
        response.setState(person.getState());
        response.setStatus(person.getStatus());
        response.setPhotoUrl(person.getPhotoUrl());

        return response;
    }

// get all missing persons with pagination
  public Page<MissingPersonResponse> getAllMissingPersons(

        String name,
        String city,
        MissingStatus status,
        Pageable pageable) {

    Specification<MissingPerson> specification =
            Specification.where(MissingPersonSpecification.hasName(name))
                    .and(MissingPersonSpecification.hasCity(city))
                    .and(MissingPersonSpecification.hasStatus(status));

    return repository.findAll(specification, pageable)
            .map(this::mapToResponse);
}



// get missiing person by case number
public MissingPersonResponse getMissingPersonById(Long id) {

    MissingPerson person = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Missing person not found"));

    MissingPersonResponse response = new MissingPersonResponse();

    response.setId(person.getId());
    response.setCaseNumber(person.getCaseNumber());
    response.setFullName(person.getFullName());
    response.setAge(person.getAge());
    response.setGender(person.getGender());
    response.setCity(person.getCity());
    response.setState(person.getState());
    response.setStatus(person.getStatus());
    response.setPhotoUrl(person.getPhotoUrl());

    return response;
}

public MissingPersonResponse updateMissingPerson(
        Long id,
        CreateMissingPersonRequest request) {

    MissingPerson person = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Missing person not found"));

    person.setFullName(request.getFullName());
    person.setAge(request.getAge());
    person.setGender(request.getGender());
    person.setBloodGroup(request.getBloodGroup());
    person.setHeight(request.getHeight());
    person.setWeight(request.getWeight());
    person.setIdentificationMarks(request.getIdentificationMarks());
    person.setLastSeenDate(request.getLastSeenDate());
    person.setLastSeenLocation(request.getLastSeenLocation());
    person.setCity(request.getCity());
    person.setState(request.getState());
    person.setCountry(request.getCountry());
    person.setDescription(request.getDescription());

    person.setUpdatedAt(LocalDateTime.now());

    person = repository.save(person);

    MissingPersonResponse response = new MissingPersonResponse();

    response.setId(person.getId());
    response.setCaseNumber(person.getCaseNumber());
    response.setFullName(person.getFullName());
    response.setAge(person.getAge());
    response.setGender(person.getGender());
    response.setCity(person.getCity());
    response.setState(person.getState());
    response.setStatus(person.getStatus());
    response.setPhotoUrl(person.getPhotoUrl());

    return response;
}


public void deleteMissingPerson(Long id) {

    MissingPerson person = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Missing person not found"));

    repository.delete(person);
}


private MissingPersonResponse mapToResponse(MissingPerson person) {

    MissingPersonResponse response = new MissingPersonResponse();

    response.setId(person.getId());
    response.setCaseNumber(person.getCaseNumber());
    response.setFullName(person.getFullName());
    response.setAge(person.getAge());
    response.setGender(person.getGender());
    response.setCity(person.getCity());
    response.setState(person.getState());
    response.setStatus(person.getStatus());
    response.setPhotoUrl(person.getPhotoUrl());

    return response;
}



}