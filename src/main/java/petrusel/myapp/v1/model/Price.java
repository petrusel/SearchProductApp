package petrusel.myapp.v1.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titleProduct;
    private String priceProduct;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String link;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Price() {}

    public Price(String titleProduct, String priceProduct, String link, Product product) {
        this.titleProduct = titleProduct;
        this.priceProduct = priceProduct;
        this.link = link;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
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
}
