package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Product;

import java.util.List;

public interface ProductDAO extends Dao<Product> {

    Product get(String url);

    void remove(String url);

    void removeByCategoryId(long categoryId);

    List<Product> getListByCategoryId(long categoryId);
}
