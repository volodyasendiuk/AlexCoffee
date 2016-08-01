package ua.com.alexcoffee.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    public Product() {
        super();
    }

    public Product(long id, String title, String url, Category category, Photo photo, double price) {
        super(id);
        this.title = title;
        this.url = url;
        this.category = category;
        this.photo = photo;
        this.price = price;
    }

    public Product(String title, String url, Category category, Photo photo, double price) {
        super();
        this.title = title;
        this.url = url;
        this.category = category;
        this.photo = photo;
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title)
                .append("\nParameters: ").append(parameters)
                .append("\nDescription: ").append(description)
                .append("\nCategory: ").append(category.getTitle())
                .append("\nPhoto: ").append(photo.getTitle())
                .append(", price = ").append(price).append(" UAH");
        return sb.toString();
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

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setAllInfo(String title, String url, String parameters,
                                  String description, Category category, Photo photo, double price) {
        setTitle(title);
        setUrl(url);
        setParameters(parameters);
        setDescription(description);
        setCategory(category);
        setPhoto(photo);
        setPrice(price);
    }
}