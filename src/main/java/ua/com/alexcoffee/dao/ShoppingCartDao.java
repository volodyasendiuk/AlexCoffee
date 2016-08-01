package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Product;
import ua.com.alexcoffee.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {

    List<Product> getProducts();

    double getPriceOfProducts();

    void addProduct(Product product);

    void removeProduct(Product product);

    void clearProducts();

    ShoppingCart get();

    int size();
}
