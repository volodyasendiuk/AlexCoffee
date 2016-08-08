package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.model.Product;

import java.util.List;

public interface ProductRepository extends ItemRepository<Product, Long> {

    Product findByUrl(String url);

    Product findByArticle(int article);

    void deleteByUrl(String url);

    List<Product> findByCategoryId(long id);
}
