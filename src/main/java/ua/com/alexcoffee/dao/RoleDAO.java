package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.enums.RoleEnum;

public interface RoleDAO extends Dao<Role> {

    void add(RoleEnum title);

    void remove(RoleEnum title);

    Role get(RoleEnum title);

    Role getDefault();
}
