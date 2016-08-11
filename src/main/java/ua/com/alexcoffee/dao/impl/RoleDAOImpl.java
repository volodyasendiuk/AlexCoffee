package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.RoleDAO;
import ua.com.alexcoffee.repository.RoleRepository;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа объектов класса {@link Role}
 * в базе данных интерфейса {@link RoleDAO}, наследует родительский
 * абстрактній класс {@link AbstractDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link RoleRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see AbstractDAOImpl
 * @see RoleDAO
 * @see Role
 * @see RoleRepository
 */
@Repository
public class RoleDAOImpl extends AbstractDAOImpl<Role> implements RoleDAO {
    /**
     * Объект репозитория для работы с БД.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать репозиторий.
     */
    @Autowired
    private RoleRepository repository;

    /**
     * Добавляет роль в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    @Override
    public void add(RoleEnum title) {
        repository.save(new Role(title));
    }

    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     */
    @Override
    public Role get(RoleEnum title) {
        return repository.findByTitle(title);
    }

    /**
     * Возвращает из базы даных роль по-умолчанию.
     *
     * @return Объект класса {@link Role} - роль по-умолчание.
     */
    @Override
    public Role getDefault() {
        return repository.findOne((long) 1);
    }

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    @Override
    public void remove(RoleEnum title) {
        repository.deleteByTitle(title);
    }

    /**
     * Возвращает объект репозитория для работы основных методов доступа к базе данных.
     *
     * @return Объект класса {@link RoleRepository} - репозиторий.
     */
    @Override
    public RoleRepository getRepository() {
        return repository;
    }
}
