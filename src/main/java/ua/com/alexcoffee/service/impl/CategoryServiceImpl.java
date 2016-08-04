package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.CategoryService;

@Service
public class CategoryServiceImpl extends ItemServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Category get(String url) throws BadRequestException, WrongInformationException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        Category category = dao.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        return category;
    }

    @Override
    @Transactional
    public void remove(String url) throws WrongInformationException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        dao.remove(url);
    }

    @Override
    public CategoryDAO getDao() {
        return dao;
    }
}
