package ua.com.alexcoffee.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Category() {
        super();
    }

    public Category(long id, String title, String url, String description, Photo photo) {
        super(id);
        this.title = title;
        this.url = url;
        this.description = description;
        this.photo = photo;
    }

    public Category(String title, String url, String description, Photo photo) {
        super();
        this.title = title;
        this.url = url;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nUrl: ").append(url)
                .append("\nDiscription: ").append(description)
                .append("\nPhoto: ").append(photo.getTitle());
        return sb.toString();
    }

    @Override
    public String toEquals() {
        return getTitle() + getUrl();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    public void clearProducts() {
        products.clear();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Photo getPhoto() {
        return photo;
    }



    public void setAllInfo(String title, String url, String description, Photo photo) {
        setTitle(title);
        setUrl(url);
        setDescription(description);
        setPhoto(photo);
    }
}
