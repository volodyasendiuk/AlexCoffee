package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Product;

import java.util.List;

public interface ProductService extends ItemService<Product> {

    Product getByUrl(String url);

    Product getByArticle(int article);

    List<Product> getByCategoryUrl(String categoryUrl);

    List<Product> getByCategoryId(Long categoryId);

    List<Product> getRandomByCategoryId(int size, Long categoryId, Long differentProductId);

    List<Product> getRandomByCategoryId(int size, Long categoryId);

    List<Product> getRandom(int size);

    void remove(String url);

    void removeByCategoryUrl(String categoryUrl);

    void removeByCategoryId(Long id);
}
