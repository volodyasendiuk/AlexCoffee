package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.model.Role;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Role} с базой данных.
 * Расширяет интерфейс {@link DAO}.
 *
 * @author Yurii Salimov
 * @see DAO
 * @see ua.com.alexcoffee.dao.impl.RoleDAOImpl
 * @see Role
 * @see RoleEnum
 * @see ua.com.alexcoffee.model.User
 */
public interface RoleDAO extends DAO<Role> {

    /**
     * Добавляет роль в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    void add(RoleEnum title);

    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     */
    Role get(RoleEnum title);

    /**
     * Возвращает из базы даных роль по-умолчанию.
     *
     * @return Объект класса {@link Role} - роль по-умолчание.
     */
    Role getDefault();

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    void remove(RoleEnum title);
}
