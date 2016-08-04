package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.enums.RoleEnum;

public interface RoleDAO extends DAO<Role> {

    void add(RoleEnum title);

    void remove(RoleEnum title);

    Role get(RoleEnum title);

    Role getDefault();
}
