package findme_backend.user.controller;

import findme_backend.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @GetMapping("/api/users/me")
    public User me(Authentication authentication) {

        return (User) authentication.getPrincipal();

    }
}