package ua.com.alexcoffee.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Product> products = new ArrayList<>();

    public ShoppingCart() {

    }

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        int count = 1;
        StringBuilder sb = new StringBuilder("Shoping Cart: ");
        for (Product product : products) {
            sb.append("\n").append(count++).append(") ").append(product.getTitle())
                    .append("\n№ ").append(product.getId()).append(", ").append(product.getPrice()).append(" грн");
        }
        sb.append("\nPrice: ").append(getPriceOfProducts()).append(" UAH");
        return sb.toString();
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

    public double getPriceOfProducts() {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public int getSize() {
        return products.size();
    }
}
