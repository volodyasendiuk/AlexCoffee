package ua.com.alexcoffee.service.impl;

import ua.com.alexcoffee.dao.RoleDAO;
import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends ItemServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDAO dao;

    @Override
    @Transactional
    public void add(RoleEnum title) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }
        if (dao.get(title) != null) {
            throw new DuplicateException("Duplicate role with title  " + title/*.name()*/ + "!");
        }
        dao.add(new Role(title));
    }

    @Override
    @Transactional(readOnly = true)
    public Role get(RoleEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }
        Role role = dao.get(title);
        if (role == null) {
            throw new BadRequestException("Can't find role by title " + title/*.name()*/ + "!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getAdministrator() throws BadRequestException {
        Role role = dao.get((long) 2);
        if (role == null) {
            throw new BadRequestException("Can't find role \"administrator\"!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getManager() throws BadRequestException {
        Role role = dao.get((long) 3);
        if (role == null) {
            throw new BadRequestException("Can't find role \"manager\"!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getDefault() throws BadRequestException {
        Role role = dao.getDefault();
        if (role == null) {
            throw new BadRequestException("Can't find default role!");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getPersonnel() {
        List<Role> roles = dao.getAll();
        if (roles.isEmpty()) {
            return null;
        }

        roles.remove(getDefault());
        return roles;
    }

    @Override
    @Transactional
    public void remove(RoleEnum title) {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }
        dao.remove(title);
    }

    @Override
    public RoleDAO getDao() {
        return dao;
    }
}
