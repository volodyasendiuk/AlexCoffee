package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.model.Category;

public interface CategoryRepository extends ItemRepository<Category, Long> {

    Category findByUrl(String url);

    void deleteByUrl(String url);
}
