package ua.com.alexcoffee.service;

import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.enums.RoleEnum;

import java.util.List;

public interface RoleService extends ItemService<Role> {

    void add(RoleEnum title);

    Role get(RoleEnum title);

    Role getAdministrator();

    Role getManager();

    Role getDefault();

    List<Role> getPersonnel();

    void remove(RoleEnum title);
}
