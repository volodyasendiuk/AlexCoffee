package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.Dao;
import ua.com.alexcoffee.dao.repository.ItemRepository;
import ua.com.alexcoffee.entity.Model;

import java.util.Collection;
import java.util.List;

public abstract class DaoAbstractImpl<T extends Model> implements Dao<T> {

    @Override
    public void add(T model) {
        getRepository().save(model);
    }

    @Override
    public void add(Collection<T> models) {
        getRepository().save(models);
    }

    @Override
    public void update(T model) {
        getRepository().save(model);
    }

    @Override
    public T get(Long id) {
        return getRepository().findOne(id);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public void remove(T model) {
        getRepository().delete(model);
    }

    @Override
    public void remove(Long id) {
        getRepository().delete(id);
    }

    @Override
    public void remove(Collection<T> models) {
        getRepository().delete(models);
    }

    @Override
    public void removeAll() {
        getRepository().deleteAll();
    }

    public abstract ItemRepository<T, Long> getRepository();
}
