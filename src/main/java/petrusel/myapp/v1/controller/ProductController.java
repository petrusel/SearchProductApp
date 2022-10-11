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

//    @GetMapping("/list")
//    public String showAllProducts(Model model) {
//        List<Product> products = productService.getAllProducts();
//        model.addAttribute("allProducts", products);
//        return "products_list";
//    }

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
        return "redirect:/product/search";
    }

    @GetMapping("/{id}/edit/{keyword}")
    public String showEditProductForm(@PathVariable Integer id, @PathVariable String keyword, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("keyword", keyword);
        return "edit_product";
    }

    @PostMapping("/{id}/edit/{keyword}")
    public String saveUpdateProduct(@PathVariable Integer id,
                                    @PathVariable String keyword,
                                    @RequestParam("file") MultipartFile file,
                                    Product product) {
        productService.updateProduct(id, file, product);
        return "redirect:/product/search?keyword=" + keyword;
    }

    @GetMapping("/{id}/delete/{keyword}")
    public String deleteProduct(@PathVariable Integer id, @PathVariable String keyword) {
        productService.deleteProduct(id);
        return "redirect:/product/search?keyword=" + keyword;
    }

    @GetMapping("/search")
    public String showProducts(String keyword, Model model) {
        List<Product> products = productService.getProdByKeyword(keyword);
        model.addAttribute("allProducts", products);
        model.addAttribute("keyword", keyword);
        return "search_product";
    }
}
