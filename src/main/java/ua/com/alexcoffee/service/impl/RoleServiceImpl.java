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

import java.util.List;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Role}
 * в базе данных интерфейса {@link RoleService}, наследует родительский
 * абстрактній класс {@link AbstractServiceImpl}, в котором реализованы
 * основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see AbstractServiceImpl
 * @see RoleService
 * @see Role
 * @see RoleDAO
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements RoleService {
    /**
     * Объект интерфейса для работы с базой данных.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    private RoleDAO dao;

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
            throw new DuplicateException("Duplicate role with title  " + title/*.name()*/ + "!");
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
        Role role = dao.get(title);
        if (role == null) {
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
        Role role = dao.get((long) 2);
        if (role == null) {
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
        Role role = dao.get((long) 3);
        if (role == null) {
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
        Role role = dao.getDefault();
        if (role == null) {
            throw new BadRequestException("Can't find default role!");
        }
        return role;
    }

    /**
     * Возвращает список ролей персонала сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список ролей.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> getPersonnel() {
        List<Role> roles = dao.getAll();
        if (roles.isEmpty()) {
            return null;
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

    /**
     * Возвращает объект DAO для работы основных методов доступа к базе данных,
     * реализованых в родительском классе {@link AbstractServiceImpl}.
     *
     * @return Объект класса {@link RoleDAO} - объект DAO.
     * @throws BadRequestException Бросает исключение, когда объект DAO равный null.
     */
    @Override
    public RoleDAO getDao() throws BadRequestException {
        if (dao == null) {
            throw new BadRequestException("Can't find role DAO!");
        }
        return dao;
    }
}
