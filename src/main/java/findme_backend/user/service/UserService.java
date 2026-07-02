package findme_backend.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import findme_backend.user.dto.RegisterRequest;
import findme_backend.user.entity.User;
import findme_backend.user.enums.Role;
import findme_backend.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(RegisterRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());
        user.setRole(Role.USER);

        if(repository.findByEmail(request.getEmail()).isPresent()) {
       throw new RuntimeException("Email already exists");
        }
        
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return repository.save(user);

    }
}

