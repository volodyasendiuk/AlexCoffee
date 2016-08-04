package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.ShoppingCartDao;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.model.Sale;
import ua.com.alexcoffee.model.ShoppingCart;
import ua.com.alexcoffee.service.ShoppingCartService;

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
    public void add(Sale sale) {
        shoppingCartDao.addSale(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sale> getSales() {
        List<Sale> sales = shoppingCartDao.getSales();
        if (sales.isEmpty()) {
            return null;
        }
        return sales;
    }

    @Override
    @Transactional
    public void remove(Sale sale) {
        shoppingCartDao.removeSale(sale);
    }

    @Override
    @Transactional
    public void clear() {
        shoppingCartDao.clearSales();
    }

    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return shoppingCartDao.getPrice();
    }

    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return shoppingCartDao.size();
    }
}
