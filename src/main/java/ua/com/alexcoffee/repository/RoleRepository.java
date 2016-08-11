package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.model.Role;

/**
 * Репозиторий для объектов класса {@link Role}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link ItemRepository}.
 *
 * @author Yurii Salimov
 * @see ItemRepository
 * @see Role
 */
public interface RoleRepository extends ItemRepository<Role, Long> {
    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     */
    Role findByTitle(RoleEnum title);

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    void deleteByTitle(RoleEnum title);
}
