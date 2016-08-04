package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product> {

    Product get(String url);

    void remove(String url);

    void removeByCategoryId(long categoryId);

    List<Product> getListByCategoryId(long categoryId);
}
