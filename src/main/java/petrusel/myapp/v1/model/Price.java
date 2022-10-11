package petrusel.myapp.v1.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String priceProduct;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String link;

    private String site;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Price() {}

    public Price(String priceProduct, String link, String site, Product product) {
        this.priceProduct = priceProduct;
        this.link = link;
        this.site = site;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", priceProduct='" + priceProduct + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
