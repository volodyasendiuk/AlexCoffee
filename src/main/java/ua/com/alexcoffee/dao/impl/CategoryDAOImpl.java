package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.dao.repository.CategoryRepository;
import ua.com.alexcoffee.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl extends DAOAbstractImpl<Category> implements CategoryDAO {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category get(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public void remove(String url) {
        repository.deleteByUrl(url);
    }

    @Override
    public CategoryRepository getRepository() {
        return repository;
    }
}
