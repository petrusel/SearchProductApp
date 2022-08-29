package petrusel.myapp.v1.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import petrusel.myapp.v1.model.Product;
import petrusel.myapp.v1.repository.ProductRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(MultipartFile file, String name, String desc) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setImage(file.getBytes());
        product.setDescription(desc);
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
    }
}
