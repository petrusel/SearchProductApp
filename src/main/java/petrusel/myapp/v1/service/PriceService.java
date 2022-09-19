package petrusel.myapp.v1.service;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import petrusel.myapp.v1.model.Price;
import petrusel.myapp.v1.model.Product;
import petrusel.myapp.v1.repository.PriceRepository;
import petrusel.myapp.v1.repository.ProductRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

@Service
public class PriceService {

    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    public PriceService(ProductRepository productRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    public void addProductLinks(Integer id, Price price) {
        Product product = productRepository.getReferenceById(id);
        Price link = new Price();
        link.setLink(price.getLink());
        link.setProduct(product);
        priceRepository.save(link);
    }

    public Document getDocument(String url) {
        Connection conn = Jsoup.connect(url);
        conn.userAgent(
                "Microsoft Edge: Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/51.0.2704.79 " +
                        "Safari/537.36 " +
                        "Edge/14.14393 ");
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public void getPrices() {
        List<Price> links = priceRepository.findAll();
        for (Price link : links) {
            Document doc = getDocument(link.getLink());
            String title = "";
            String price = "";
            if (link.getLink().contains("emag")) {
                Element pricingBlock = doc.select("div.pricing-block.has-installments").first();

                title = doc.select("h1.page-title").text();

                if (pricingBlock.select("p.product-new-price.has-deal").text().equals("")) {
                    price = pricingBlock.select("p.product-new-price").text();
                } else {
                    price = pricingBlock.select("p.product-new-price.has-deal").text();
                }
            } else if (link.getLink().contains("flanco")) {

            } else if (link.getLink().contains("cel")) {

            } else {
                System.out.println("The shop not exists in the list.");
            }
            link.setTitleProduct(title);
            link.setPriceProduct(price);
        }
    }
}
