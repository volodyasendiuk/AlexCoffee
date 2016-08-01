package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Photo;

public interface PhotoDAO extends Dao<Photo> {

    void delete(String name);

    Photo get(String name);
}
