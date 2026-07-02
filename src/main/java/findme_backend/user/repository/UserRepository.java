package findme_backend.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import findme_backend.user.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
