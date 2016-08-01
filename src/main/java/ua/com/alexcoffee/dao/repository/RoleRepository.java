package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.entity.Role;
import ua.com.alexcoffee.enums.RoleEnum;

public interface RoleRepository extends ItemRepository<Role, Long> {

    Role findByTitle(RoleEnum title);

    void deleteByTitle(RoleEnum title);
}
