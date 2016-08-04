package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.ShoppingCartDao;
import ua.com.alexcoffee.model.Sale;
import ua.com.alexcoffee.model.ShoppingCart;

import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Autowired
    private ShoppingCart shoppingCart;

    @Override
    public List<Sale> getSales() {
        return shoppingCart.getSales();
    }

    @Override
    public double getPrice() {
        return shoppingCart.getPrice();
    }

    @Override
    public void addSale(Sale sale) {
        shoppingCart.addSale(sale);
    }

    @Override
    public void removeSale(Sale sale) {
        shoppingCart.removeSale(sale);
    }

    @Override
    public void clearSales() {
        shoppingCart.clearSales();
    }

    @Override
    public ShoppingCart get() {
        return shoppingCart;
    }

    @Override
    public int size() {
        return shoppingCart.getSize();
    }
}
