package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Category;

public interface CategoryDAO extends DAO<Category> {

    Category get(String url);

    void remove(String url);
}
