package ua.com.alexcoffee.service.impl;

import ua.com.alexcoffee.dao.RoleDAO;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Role}
 * в базе данных интерфейса {@link RoleService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see MainServiceImpl
 * @see RoleService
 * @see Role
 * @see RoleDAO
 */
@Service
public class RoleServiceImpl extends MainServiceImpl<Role> implements RoleService {
    /**
     * Реализация интерфейса {@link RoleDAO} для работы ролей пользователей с базой данных.
     */
    private RoleDAO dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param dao Реализация интерфейса {@link RoleDAO} для работы ролей пользователей с базой данных.
     */
    @Autowired
    public RoleServiceImpl(RoleDAO dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Добавляет роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли для добавления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws DuplicateException        Бросает исключение, если в БД уже есть такой объект.
     */
    @Override
    @Transactional
    public void add(RoleEnum title) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }

        if (dao.get(title) != null) {
            throw new DuplicateException("Duplicate role with title  " + title + "!");
        }
        dao.add(new Role(title));
    }

    /**
     * Возвращает роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     * Режим только для чтения.
     *
     * @param title Название роли для возврата.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws BadRequestException       Бросает исключение, если не найденая рось с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Role get(RoleEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }

        Role role;
        try {
            role = dao.get(title);
        } catch (NullPointerException ex) {
            throw new BadRequestException("Can't find role by title " + title + "!");
        }

        return role;
    }

    /**
     * Возвращает роль администратора. Режим только для чтения.
     *
     * @return Объект класса {@link Role} - роль администратора.
     * @throws BadRequestException Бросает исключение, если не найдена роль-админ.
     */
    @Override
    @Transactional(readOnly = true)
    public Role getAdministrator() throws BadRequestException {
        Role role;
        try {
            role = dao.get(RoleEnum.ADMIN);
        } catch (NullPointerException ex) {
            throw new BadRequestException("Can't find role \"administrator\"!");
        }

        return role;
    }

    /**
     * Возвращает роль менеджера. Режим только для чтения.
     *
     * @return Объект класса {@link Role} - роль менеджера.
     * @throws BadRequestException Бросает исключение, когда не найдена роль-менеджер.
     */
    @Override
    @Transactional(readOnly = true)
    public Role getManager() throws BadRequestException {
        Role role;
        try {
            role = dao.get(RoleEnum.MANAGER);
        } catch (NullPointerException ex) {
            throw new BadRequestException("Can't find role \"manager\"!");
        }

        return role;
    }

    /**
     * Возвращает роль по-умолчанию. Режим только для чтения.
     *
     * @return Объект класса {@link Role} - роль по-умолчание.
     * @throws BadRequestException Бросает исключение, если не найдена роль по-умолчание.
     */
    @Override
    @Transactional(readOnly = true)
    public Role getDefault() throws BadRequestException {
        Role role;
        try {
            role = dao.getDefault();
        } catch (NullPointerException ex) {
            throw new BadRequestException("Can't find default role!");
        }
        return role;
    }

    /**
     * Возвращает список ролей персонала сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список ролей персонала.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> getPersonnel() {
        List<Role> roles = dao.getAll();
        if (roles.isEmpty()) {
            return Collections.emptyList();
        }

        roles.remove(getDefault());
        return roles;
    }

    /**
     * Удаляет роль по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     */
    @Override
    @Transactional
    public void remove(RoleEnum title) throws WrongInformationException {
        if (title == null) {
            throw new WrongInformationException("No role enum (title)!");
        }
        dao.remove(title);
    }
}
