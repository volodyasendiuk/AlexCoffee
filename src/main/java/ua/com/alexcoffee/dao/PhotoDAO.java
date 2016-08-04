package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Photo;

public interface PhotoDAO extends DAO<Photo> {

    void delete(String name);

    Photo get(String name);
}
