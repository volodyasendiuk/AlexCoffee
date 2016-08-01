package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.RoleDAO;
import ua.com.alexcoffee.dao.repository.RoleRepository;
import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends DaoAbstractImpl<Role> implements RoleDAO {

    @Autowired
    private RoleRepository repository;

    @Override
    public void add(RoleEnum title) {
        repository.save(new Role(title));
    }

    @Override
    public void remove(RoleEnum title) {
        repository.deleteByTitle(title);
    }

    @Override
    public Role get(RoleEnum title) {
        return repository.findByTitle(title);
    }

    @Override
    public Role getDefault() {
        return repository.findOne((long) 1);
    }

    @Override
    public RoleRepository getRepository() {
        return repository;
    }
}
