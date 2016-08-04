package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.model.Photo;

public interface PhotoRepository extends ItemRepository<Photo, Long> {

    Photo findByTitle(String title);
}
