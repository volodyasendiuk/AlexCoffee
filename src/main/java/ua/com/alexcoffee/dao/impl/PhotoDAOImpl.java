package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.PhotoDAO;
import ua.com.alexcoffee.dao.repository.PhotoRepository;
import ua.com.alexcoffee.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoDAOImpl extends DAOAbstractImpl<Photo> implements PhotoDAO {

    @Autowired
    private PhotoRepository repository;

    @Override
    public void delete(String title) {
        Photo photo = repository.findByTitle(title);
        repository.delete(photo);
    }

    @Override
    public Photo get(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public PhotoRepository getRepository() {
        return repository;
    }
}
