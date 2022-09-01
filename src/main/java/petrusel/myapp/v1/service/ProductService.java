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

    public void addProduct(MultipartFile file, String name, String desc) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("File is not valid!");
        }
        product.setName(name);
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setDescription(desc);
        productRepository.save(product);
    }

    public void updateProduct(Integer id, MultipartFile file, String name, String desc) {
        Product product = productRepository.getReferenceById(id);
        product.setName(name);
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setDescription(desc);
        productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepository.getReferenceById(id);
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
    }
}
