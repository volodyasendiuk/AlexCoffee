package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.ShoppingCartDAO;
import ua.com.alexcoffee.model.SalePosition;
import ua.com.alexcoffee.model.ShoppingCart;

import java.util.List;

/**
 * Класс реализует методы интерфейса {@link ShoppingCartDAO} для работы с корзиной.
 *
 * @author Yurii Salimov
 * @see AbstractDAOImpl
 * @see ShoppingCartDAO
 * @see ShoppingCart
 */
@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {
    /**
     * Объект корзина, в которой хранятся торговые позиции клиента.
     */
    @Autowired
    private ShoppingCart shoppingCart;

    /**
     * Возвращает список всех торговых позиций в корзине.
     *
     * @return Объект типа {@link List} - список торговых позиций.
     */
    @Override
    public List<SalePosition> getSalePositions() {
        return shoppingCart.getSalePositions();
    }

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция, которая будет добавлена в корзину.
     */
    @Override
    public void addSalePosition(SalePosition salePosition) {
        shoppingCart.addSalePosition(salePosition);
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для удаления из корзины.
     */
    @Override
    public void removeSalePosition(SalePosition salePosition) {
        shoppingCart.removeSalePosition(salePosition);
    }

    /**
     * Очищает корзину. Удаляет все торговые позиции в корзине.
     */
    @Override
    public void clearSalePositions() {
        shoppingCart.clearSalePositions();
    }

    /**
     * Возвращает объект-корзину целиком.
     *
     * @return Объект класса {@link ShoppingCart} - корзина.
     */
    @Override
    public ShoppingCart get() {
        return shoppingCart;
    }

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    @Override
    public int size() {
        return shoppingCart.getSize();
    }

    /**
     * Возвращает цену корзины - цена всех продаж.
     *
     * @return Значение типа double - цена корзины.
     */
    @Override
    public double getPrice() {
        return shoppingCart.getPrice();
    }
}
