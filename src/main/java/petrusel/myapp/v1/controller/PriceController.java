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

@Controller
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    // add endpoints for mapping the button to get and update the prices

    @GetMapping("/product/{id}/addLink")
    public String addLink(@PathVariable Integer id, Model model) {
        Price price = new Price();
        model.addAttribute("link", price);
        return "add_link";
    }

    @PostMapping("/product/{id}/addLink")
    public String saveLink(@PathVariable Integer id, Price price) {
        priceService.addProductLinks(id, price);
        return "redirect:/product/{id}/addLink?success";
    }

    @RequestMapping(value="/takePrices")
    public String updatePrices() {
        priceService.getPrices();
        return "redirect:/product/list?success";
    }
}
