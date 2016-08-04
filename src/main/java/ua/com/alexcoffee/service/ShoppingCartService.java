package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Sale;
import ua.com.alexcoffee.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart();

    void add(Sale product);

    List<Sale> getSales();

    void remove(Sale sale);

    void clear();

    double getPrice();

    int getSize();
}
