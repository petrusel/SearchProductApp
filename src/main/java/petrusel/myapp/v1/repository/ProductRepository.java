package petrusel.myapp.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petrusel.myapp.v1.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
