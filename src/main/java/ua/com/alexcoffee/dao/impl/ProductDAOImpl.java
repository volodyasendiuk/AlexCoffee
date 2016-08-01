package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.ProductDAO;
import ua.com.alexcoffee.dao.repository.ProductRepository;
import ua.com.alexcoffee.entity.Product;

import java.util.List;

@Repository
public class ProductDAOImpl extends DaoAbstractImpl<Product> implements ProductDAO {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product get(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public void remove(String url) {
        repository.deleteByUrl(url);
    }

    @Override
    public void removeByCategoryId(long categoryId) {
        List<Product> productList = repository.findByCategoryId(categoryId);
        repository.delete(productList);
    }

    @Override
    public List<Product> getListByCategoryId(long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    @Override
    public ProductRepository getRepository() {
        return repository;
    }
}
