package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import petrusel.myapp.v1.model.Product;
import petrusel.myapp.v1.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("allProducts", products);
        return "all_products";
    }

    @GetMapping("/addProduct")
    public String newProductForm() {
        return "add_product";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("pname") String name,
            @RequestParam("desc") String desc
        ) {
        productService.addProduct(file, name, desc);
        return "redirect:/allProducts";
    }
}
