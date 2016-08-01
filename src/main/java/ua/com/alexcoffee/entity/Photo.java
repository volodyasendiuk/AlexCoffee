package ua.com.alexcoffee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "photo_link_short")
    private String photoLinkShort;

    @Column(name = "photo_link_long")
    private String photoLinkLong;

    @OneToMany(mappedBy = "photo")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "photo")
    private List<Category> categories = new ArrayList<>();

    public Photo() {
        super();
    }

    public Photo(long id, String title, String photoLinkShort, String photoLinkLong) {
        super(id);
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        this.photoLinkLong = photoLinkLong;
    }

    public Photo(String title, String photoLinkShort, String photoLinkLong) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        this.photoLinkLong = photoLinkLong;
    }

    public Photo(String title, String photoLinkShort) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nphoto short link: ").append(photoLinkShort)
                .append("\nphoto long link: ").append(photoLinkLong);
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoLinkShort() {
        return photoLinkShort;
    }

    public void setPhotoLinkShort(String photoLinkShort) {
        this.photoLinkShort = photoLinkShort;
    }

    public String getPhotoLinkLong() {
        return photoLinkLong;
    }

    public void setPhotoLinkLong(String photoLinkLong) {
        this.photoLinkLong = photoLinkLong;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setAllInfo(String title, String photoLinkShort, String photoLinkLong) {
        setTitle(title);
        setPhotoLinkShort(photoLinkShort);
        setPhotoLinkLong(photoLinkLong);
    }
}
