package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Category;

public interface CategoryService extends ItemService<Category> {

    Category get(String url);

    void remove(String url);
}
