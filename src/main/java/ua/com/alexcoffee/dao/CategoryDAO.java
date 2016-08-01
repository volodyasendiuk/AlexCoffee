package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Category;

public interface CategoryDAO extends Dao<Category> {

    Category get(String url);

    void remove(String url);
}
