package findme_backend.missingperson.controller;
import findme_backend.missingperson.dto.CreateMissingPersonRequest;
import findme_backend.missingperson.dto.MissingPersonResponse;
import findme_backend.missingperson.enums.MissingStatus;
import findme_backend.missingperson.service.MissingPersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missing-persons")
public class MissingPersonController {

    private final MissingPersonService missingPersonService;

    public MissingPersonController(MissingPersonService missingPersonService) {
        this.missingPersonService = missingPersonService;
    }

    // to create a missing person

    @PostMapping
    public MissingPersonResponse createMissingPerson(
            @Valid @RequestBody CreateMissingPersonRequest request,
            Authentication authentication) {

                 System.out.println(">>> Controller Executed");
        return missingPersonService.createMissingPerson(request, authentication);
    }



       // to get all missing persons by default pagination of 10 per page, sorted by createdAt in descending order
        @GetMapping
public Page<MissingPersonResponse> getAllMissingPersons(

        @RequestParam(required = false) String name,

        @RequestParam(required = false) String city,

        @RequestParam(required = false) MissingStatus status,

        @PageableDefault(
                size = 10,
                sort = "createdAt",
                direction = Sort.Direction.DESC)
        Pageable pageable) {

    return missingPersonService.getAllMissingPersons(
            name,
            city,
            status,
            pageable);

}

// to get a missing person by id
       @GetMapping("/{id}")
    public MissingPersonResponse getMissingPersonById(@PathVariable Long id) {
    return missingPersonService.getMissingPersonById(id);
}

// to update a missing person
      @PutMapping("/{id}")
      public MissingPersonResponse updateMissingPerson(
        @PathVariable Long id,
        @RequestBody CreateMissingPersonRequest request) {

    return missingPersonService.updateMissingPerson(id, request);
}

// to delete a missing person
 @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteMissingPerson(@PathVariable Long id) {
        missingPersonService.deleteMissingPerson(id);
    }




}

