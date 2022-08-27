package petrusel.myapp.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petrusel.myapp.v1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
