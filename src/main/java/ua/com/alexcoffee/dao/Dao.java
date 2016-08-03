package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Model;

import java.util.Collection;
import java.util.List;

public interface Dao<T extends Model> {

    void add(T model);

    void add(Collection<T> models);

    void update(T model);

    T get(Long id);

    List<T> getAll();

    void remove(T model);

    void remove(Long id);

    void remove(Collection<T> models);

    void removeAll();
}