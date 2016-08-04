package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;

import java.util.List;

public interface UserDAO extends DAO<User> {

    void remove(String name);

    void remove(Role role);

    User getByName(String name);

    User getByUsername(String username);

    User getAdministrator();

    List<User> getAdministrators();

    List<User> getManagers();

    List<User> getClients();

    User getAuthenticatedUser();
}
