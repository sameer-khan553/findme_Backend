package findme_backend.auth.service;


import findme_backend.auth.dto.LoginRequest;
import findme_backend.auth.dto.LoginResponse;
import findme_backend.auth.jwt.JwtService;
import findme_backend.user.entity.User;
import findme_backend.user.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

            System.out.println("Login email: " + request.getEmail());
        // Find user by email
        User user = repository.findByEmail(request.getEmail())

        .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        System.out.println("User found: " + user.getEmail());
        System.out.println("DB Password: " + user.getPassword());

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtService.generateToken(user.getEmail());

        // Return response
        return new LoginResponse(token);
    }
}
