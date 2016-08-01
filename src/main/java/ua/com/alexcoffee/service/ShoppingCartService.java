package ua.com.alexcoffee.service;

import ua.com.alexcoffee.entity.Product;
import ua.com.alexcoffee.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart();

    void add(Product product);

    List<Product> getProducts();

    void remove(Product product);

    void clear();

    double getPrice();

    int getSize();
}
