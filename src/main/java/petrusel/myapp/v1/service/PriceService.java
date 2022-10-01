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
        for(Price link : links) {
            Document doc = getDocument(link.getLink());
            String price = "";
            Element priceBlock;
            if (link.getLink().contains("emag")) {
                priceBlock = doc.select("div.pricing-block.has-installments").first();

                if (priceBlock.select("p.product-new-price.has-deal").text().equals("")) {
                    price = priceBlock.select("p.product-new-price").text();
                } else {
                    price = priceBlock.select("p.product-new-price.has-deal").text();
                }
            } else if (link.getLink().contains("flanco")) {
                priceBlock = doc.getElementsByClass("price-box price-final_price").first();

                if (priceBlock.select("span.special-price").text().equals("")) {
                    price = priceBlock.select("span.singlePrice").text();
                } else {
                    price = priceBlock.select("span.special-price").text();
                }
            } else if (link.getLink().contains("cel")) {
                priceBlock = doc.getElementsByClass("pret_tabela").first();
                price = priceBlock.select("span#product-price").text() + " Lei";
            } else {
                price = "null";
            }
            link.setPriceProduct(price);
            priceRepository.save(link);
        }
    }

    public List<Price> getLinks(Integer id) {
        Product product = productRepository.getReferenceById(id);
        return product.getPrices();
    }

    public void deleteLink(Integer id) {
        Price link = priceRepository.getReferenceById(id);
        priceRepository.delete(link);
    }

    public void updateLink(Integer idLink, Price link) {
        Price existingLink = priceRepository.getReferenceById(idLink);
        existingLink.setLink(link.getLink());
        priceRepository.save(existingLink);
    }

    public Price getLinkById(Integer idLink) {
        return priceRepository.getReferenceById(idLink);
    }
}
