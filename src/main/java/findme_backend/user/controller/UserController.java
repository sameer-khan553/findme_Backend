package findme_backend.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import findme_backend.user.dto.RegisterRequest;
import findme_backend.user.entity.User;
import findme_backend.user.service.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(
            @Valid @RequestBody RegisterRequest request) {

        return service.register(request);
            }
    }
