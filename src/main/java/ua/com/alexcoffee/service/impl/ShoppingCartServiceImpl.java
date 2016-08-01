package ua.com.alexcoffee.service.impl;

import ua.com.alexcoffee.dao.ShoppingCartDao;
import ua.com.alexcoffee.entity.Product;
import ua.com.alexcoffee.entity.ShoppingCart;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() throws BadRequestException {
        ShoppingCart shoppingCart = shoppingCartDao.get();
        if (shoppingCart == null) {
            throw new BadRequestException("Can't find shopping cart!");
        }
        return shoppingCart;
    }

    @Override
    @Transactional
    public void add(Product product) {
        shoppingCartDao.addProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        List<Product> productList = shoppingCartDao.getProducts();
        if (productList.isEmpty()) {
            return null;
        }
        return productList;
    }

    @Override
    @Transactional
    public void remove(Product product) {
        shoppingCartDao.removeProduct(product);
    }

    @Override
    @Transactional
    public void clear() {
        shoppingCartDao.clearProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return shoppingCartDao.getPriceOfProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return shoppingCartDao.size();
    }
}
