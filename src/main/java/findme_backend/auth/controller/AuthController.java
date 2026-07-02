package findme_backend.auth.controller;

import findme_backend.auth.dto.LoginRequest;
import findme_backend.auth.dto.LoginResponse;
import jakarta.validation.Valid;
import findme_backend.auth.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return service.login(request);
    }
}