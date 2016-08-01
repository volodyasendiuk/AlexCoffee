package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.UserDAO;
import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.entity.User;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ItemServiceImpl<User> implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) throws BadRequestException, WrongInformationException {
        if (name.isEmpty()) {
            throw new WrongInformationException("No user name!");
        }
        User user = userDAO.getByName(name);
        if (user == null) {
            throw new BadRequestException("Can't find user by name " + name + "!");
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) throws BadRequestException, WrongInformationException {
        if (username.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        User user = userDAO.getByUsername(username);
        if (user == null) {
            throw new BadRequestException("Can't find user by username " + username + "!");
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getAdministrator() throws BadRequestException {
        User administrator = userDAO.getAdministrator();
        if (administrator == null) {
            throw new BadRequestException("Can't find administrator!");
        }
        return administrator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAdministrators() {
        return userDAO.getAdministrators();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getManagers() {
        return userDAO.getManagers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getClients() {
        return userDAO.getClients();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getPersonnel() {
        List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public String getAuthentificatedUserName() {
        return userDAO.getAuthentificatedUserName();
    }

    @Override
    @Transactional
    public void removeByName(String name) throws WrongInformationException {
        if (name.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        userDAO.remove(name);
    }

    @Override
    @Transactional
    public void removeByRole(Role role) throws WrongInformationException {
        if (role == null) {
            throw new WrongInformationException("No user role!");
        }
        userDAO.remove(role);
    }

    @Override
    @Transactional
    public void removePersonnel() {
        List<User> personnel = getPersonnel();

        if (personnel.isEmpty()) {
            return;
        }

        personnel.remove(getAdministrator());
        userDAO.remove(personnel);
    }

    @Override
    public UserDAO getDao() {
        return userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }
        return user;
    }
}
