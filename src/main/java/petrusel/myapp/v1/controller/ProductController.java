package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petrusel.myapp.v1.model.Product;
import petrusel.myapp.v1.service.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("allProducts", products);
        return "products_list";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product";
    }

    @PostMapping("/new")
    public String addNewProduct(
            @RequestParam("file") MultipartFile file,
            Product product
        ) throws IOException {
        productService.addProduct(file, product);
        return "redirect:/product/list";
    }

    @GetMapping("/{id}/edit")
    public String showEditProductForm(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/{id}/edit")
    public String saveUpdateProduct(@PathVariable Integer id,
                                    @RequestParam("file") MultipartFile file,
                                    Product product) {
        productService.updateProduct(id, file, product);
        return "redirect:/product/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }
}
