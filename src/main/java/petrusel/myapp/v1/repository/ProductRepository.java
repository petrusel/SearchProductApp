package petrusel.myapp.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import petrusel.myapp.v1.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from products p where p.name like %:keyword%", nativeQuery = true)
    List<Product> getProducts(@Param("keyword") String keyword);
}
