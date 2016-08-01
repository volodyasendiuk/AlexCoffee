package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.ShoppingCartDao;
import ua.com.alexcoffee.entity.Product;
import ua.com.alexcoffee.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Autowired
    private ShoppingCart shoppingCart;

    @Override
    public List<Product> getProducts() {
        return shoppingCart.getProducts();
    }

    @Override
    public double getPriceOfProducts() {
        return shoppingCart.getPriceOfProducts();
    }

    @Override
    public void addProduct(Product product) {
        shoppingCart.addProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        shoppingCart.removeProduct(product);
    }

    @Override
    public void clearProducts() {
        shoppingCart.clearProducts();
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
