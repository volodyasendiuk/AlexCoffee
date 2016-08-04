package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.StatusDAO;
import ua.com.alexcoffee.dao.repository.StatusRepository;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDAOImpl extends DAOAbstractImpl<Status> implements StatusDAO {

    @Autowired
    private StatusRepository repository;

    @Override
    public void add(StatusEnum title) {
        repository.save(new Status(title));
    }

    @Override
    public void remove(StatusEnum title) {
        repository.deleteByTitle(title);
    }

    @Override
    public Status get(StatusEnum title) {
        return repository.findByTitle(title);
    }

    @Override
    public Status getDefault() {
        return repository.findOne((long) 1);
    }

    @Override
    public StatusRepository getRepository() {
        return repository;
    }
}
