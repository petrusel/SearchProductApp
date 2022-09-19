package petrusel.myapp.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petrusel.myapp.v1.model.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
