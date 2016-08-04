package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Sale;
import ua.com.alexcoffee.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {

    List<Sale> getSales();

    double getPrice();

    void addSale(Sale sale);

    void removeSale(Sale sale);

    void clearSales();

    ShoppingCart get();

    int size();
}
