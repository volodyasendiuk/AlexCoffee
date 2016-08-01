package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.entity.Photo;

public interface PhotoRepository extends ItemRepository<Photo, Long> {

    Photo findByTitle(String title);
}
