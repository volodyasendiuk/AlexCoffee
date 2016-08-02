package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.UserDAO;
import ua.com.alexcoffee.dao.repository.RoleRepository;
import ua.com.alexcoffee.dao.repository.UserRepository;
import ua.com.alexcoffee.entity.User;
import ua.com.alexcoffee.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends DaoAbstractImpl<User> implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void remove(String name) {
        userRepository.deleteByName(name);
    }

    @Override
    public void remove(Role role) {
        userRepository.deleteAllByRole(role);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getAdministrator() {
        Role admin = roleRepository.findOne((long) 2);
        return userRepository.findAllByRole(admin).get(0);
    }

    @Override
    public List<User> getAdministrators() {
        Role admin = roleRepository.findOne((long) 2);
        return userRepository.findAllByRole(admin);
    }

    @Override
    public List<User> getManagers() {
        Role manager = roleRepository.findOne((long) 3);
        return userRepository.findAllByRole(manager);
    }

    @Override
    public List<User> getClients() {
        Role client = roleRepository.findOne((long) 1);
        return userRepository.findAllByRole(client);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }
}
