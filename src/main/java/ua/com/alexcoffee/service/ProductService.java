package ua.com.alexcoffee.service;

import ua.com.alexcoffee.entity.Product;

import java.util.List;

public interface ProductService extends ItemService<Product> {

    Product get(String url);

    List<Product> getByCategoryUrl(String categoryUrl);

    List<Product> getByCategoryId(Long categoryId);

    List<Product> getRandomByCategoryId(int size, Long categoryId, Long differentProductId);

    List<Product> getRandomByCategoryId(int size, Long categoryId);

    List<Product> getRandom(int size);

    void remove(String url);

    void removeByCategoryUrl(String categoryUrl);

    void removeByCategoryId(Long id);
}
