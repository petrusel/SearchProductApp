package petrusel.myapp.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petrusel.myapp.v1.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
