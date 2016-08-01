package ua.com.alexcoffee.service.impl;

import ua.com.alexcoffee.dao.Dao;
import ua.com.alexcoffee.entity.Model;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.service.ItemService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class ItemServiceImpl<T extends Model> implements ItemService<T> {

    @Override
    @Transactional
    public void add(T model) {
        getDao().add(model);
    }

    @Override
    @Transactional
    public void add(List<T> models) {
        getDao().add(models);
    }

    @Override
    @Transactional
    public void update(T model) {
        getDao().update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public T get(Long id) throws BadRequestException {
        T model = getDao().get(id);
        if (model == null) {
            throw new BadRequestException("Can't find model by id " + id + "!");
        }
        return getDao().get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return getDao().getAll();
    }

    @Override
    @Transactional
    public void remove(T model) {
        getDao().remove(model);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        getDao().remove(id);
    }

    @Override
    @Transactional
    public void remove(List<T> models) {
        getDao().remove(models);
    }

    @Override
    @Transactional
    public void removeAll() {
        getDao().removeAll();
    }

    public abstract Dao<T> getDao();
}
