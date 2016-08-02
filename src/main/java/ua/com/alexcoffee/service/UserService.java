package ua.com.alexcoffee.service;

import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.entity.User;

import java.util.List;

public interface UserService extends ItemService<User> {

    User getByName(String name);

    User getByUsername(String username);

    User getAdministrator();

    List<User> getAdministrators();

    List<User> getManagers();

    List<User> getClients();

    List<User> getPersonnel();

    User getAuthenticatedUser();

    void removeByName(String name);

    void removeByRole(Role role);

    void removePersonnel();
}
