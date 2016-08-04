package ua.com.alexcoffee.model;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    private Sale sale;

    public Product() {
        super();
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

    @Override
    public String toEquals() {
        return getTitle() + getUrl() + getPrice();
    }

    public void initializer(String title, String url, String parameters,
                            String description, Category category, Photo photo, double price) {
        setTitle(title);
        setUrl(url);
        setParameters(parameters);
        setDescription(description);
        setCategory(category);
        setPhoto(photo);
        setPrice(price);
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
        this.price = price > 0 ? price : 0;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}