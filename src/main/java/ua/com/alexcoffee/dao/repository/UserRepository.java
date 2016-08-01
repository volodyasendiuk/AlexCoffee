package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.entity.User;

import java.util.List;

public interface UserRepository extends ItemRepository<User, Long> {

    User findByName(String name);

    User findByUsername(String username);

    User findByRole(Role role);

    List<User> findAllByRole(Role role);

    void deleteAllByRole(Role role);

    void deleteByName(String name);
}
