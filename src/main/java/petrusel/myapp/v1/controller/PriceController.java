package petrusel.myapp.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import petrusel.myapp.v1.model.Price;
import petrusel.myapp.v1.model.Product;
import petrusel.myapp.v1.service.PriceService;
import petrusel.myapp.v1.service.ProductService;

import java.util.List;

@Controller
public class PriceController {

    private final PriceService priceService;
    private final ProductService productService;

    public PriceController(PriceService priceService, ProductService productService) {
        this.priceService = priceService;
        this.productService = productService;
    }

    @GetMapping("/product/{id}/addLink/{keyword}")
    public String addLink(@PathVariable Integer id, @PathVariable String keyword, Model model) {
        Price price = new Price();
        Product product = productService.getProductById(id);
        model.addAttribute("link", price);
        model.addAttribute("product", product);
        return "add_link";
    }

    @PostMapping("/product/{id}/addLink/{keyword}")
    public String saveLink(@PathVariable Integer id, @PathVariable String keyword, Price price) {
        priceService.addProductLinks(id, price);
        return "redirect:/product/{id}/addLink/a?success";
    }

    @RequestMapping(value="/takePrices")
    public String updatePrices() {
        priceService.getPrices();
        return "redirect:/product/search?success";
    }

    @GetMapping("/product/{id}/links")
    public String showLinks(@PathVariable Integer id, Model model) {
        List<Price> links = priceService.getLinks(id);
        model.addAttribute("allLinks", links);
        return "links_list";
    }

    @GetMapping("/product/{idProduct}/link/{idLink}/delete/{keyword}")
    public String removeLink(@PathVariable Integer idLink, @PathVariable String keyword) {
        priceService.deleteLink(idLink);
        return "redirect:/product/{idProduct}/prices/" + keyword;
    }

    @GetMapping("/product/{idProduct}/link/{idLink}/edit/{keyword}")
    public String editLinkForm(@PathVariable Integer idProduct, @PathVariable Integer idLink, @PathVariable String keyword, Model model) {
        Price price = priceService.getLinkById(idLink);
        Product product = productService.getProductById(idProduct);
        model.addAttribute("link", price);
        model.addAttribute("product", product);

        return "edit_link";
    }

    @PostMapping("/product/{idProduct}/link/{idLink}/edit/{keyword}")
    public String saveEditLink(@PathVariable Integer idProduct, @PathVariable Integer idLink, @PathVariable String keyword, Price price) {
        priceService.updateLink(idLink, price);
        return "redirect:/product/{idProduct}/prices/" + keyword;
    }

    @GetMapping("/product/{idProduct}/prices/{keyword}")
    public String showPrices(@PathVariable Integer idProduct, @PathVariable String keyword, Model model) {
        List<Price> prices = priceService.getLinks(idProduct);
        Product product = productService.getProductById(idProduct);
        model.addAttribute("product", product);
        model.addAttribute("prices", prices);
        model.addAttribute("keyword", keyword);
        return "prices_list";
    }
}
