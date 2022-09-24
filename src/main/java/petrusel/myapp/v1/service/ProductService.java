package petrusel.myapp.v1.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import petrusel.myapp.v1.model.Product;
import petrusel.myapp.v1.repository.ProductRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(MultipartFile file, Product product) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("File is not valid!");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productRepository.save(product);
    }

    public void updateProduct(Integer id, MultipartFile file, Product product) {
        Product existingProduct = productRepository.getReferenceById(id);
        existingProduct.setName(product.getName());
        try {
            if(!file.getOriginalFilename().equals("")) {
                existingProduct.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        existingProduct.setDescription(product.getDescription());
        productRepository.save(existingProduct);
    }

    public Product getProductById(Integer id) {
        return productRepository.getReferenceById(id);
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
    }

    public List<Product> getProdByKeyword(String keyword) {
        return productRepository.getProducts(keyword);
    }
}
