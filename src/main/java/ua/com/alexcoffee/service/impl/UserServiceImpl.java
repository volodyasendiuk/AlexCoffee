package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.UserDAO;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link User}
 * в базе данных интерфейса {@link UserService}, наследует родительский
 * абстрактній класс {@link AbstractServiceImpl}, в котором реализованы
 * основные методы, а также методы интерфейса {@link UserDetailsService}.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see AbstractServiceImpl
 * @see UserService
 * @see User
 * @see UserDAO
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService, UserDetailsService {
    /**
     * Объект интерфейса для работы с базой данных.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    private UserDAO dao;

    /**
     * Возвращает пользователя, у которого совпадает имя с
     * значением входящего параметра. Режим только для чтения.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     * @throws WrongInformationException Бросает исключение, когда пустой входной параметр name.
     * @throws BadRequestException       Бросает исключение, если не найден пользователь с входящим параметром name.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) throws WrongInformationException, BadRequestException {
        if (name.isEmpty()) {
            throw new WrongInformationException("No user name!");
        }
        User user = dao.getByName(name);
        if (user == null) {
            throw new BadRequestException("Can't find user by name " + name + "!");
        }
        return user;
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр username.
     * @throws BadRequestException       Бросает исключение, если не найден пользователь с входящим параметром username.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) throws WrongInformationException, BadRequestException {
        if (username.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        User user = dao.getByUsername(username);
        if (user == null) {
            throw new BadRequestException("Can't find user by username " + username + "!");
        }
        return user;
    }

    /**
     * Возвращает главного администратора сайта. Режим только для чтения.
     *
     * @return Объект класса {@link User} - главный администратор.
     * @throws BadRequestException Бросает исключение, если не найден пользователь-админ.
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws BadRequestException {
        User administrator = dao.getMainAdministrator();
        if (administrator == null) {
            throw new BadRequestException("Can't find administrator!");
        }
        return administrator;
    }

    /**
     * Возвращает список всех администраторов сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAdministrators() {
        return dao.getAdministrators();
    }

    /**
     * Возвращает список всех менеджеров сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getManagers() {
        return dao.getManagers();
    }

    /**
     * Возвращает список всех клиентов сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getClients() {
        return dao.getClients();
    }

    /**
     * Возвращает список персонала сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список персонала.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getPersonnel() {
        List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

    /**
     * Возвращает авторизированого пользователя. Режим только для чтения.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     * @throws BadRequestException Бросает исключение, если не найден авторизированый пользователь.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() throws BadRequestException {
        User user = dao.getAuthenticatedUser();
        if (user == null) {
            throw new BadRequestException("Can't find authenticated user!");
        }
        return user;
    }

    /**
     * Удаляет пользователя, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр username.
     */
    @Override
    @Transactional
    public void removeByName(String name) throws WrongInformationException {
        if (name.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        dao.remove(name);
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр role.
     */
    @Override
    @Transactional
    public void removeByRole(Role role) throws WrongInformationException {
        if (role == null) {
            throw new WrongInformationException("No user role!");
        }
        dao.remove(role);
    }

    /**
     * Удаляет список персонала сайта.
     */
    @Override
    @Transactional
    public void removePersonnel() {
        List<User> personnel = getPersonnel();
        if (personnel.isEmpty()) {
            return;
        }
        personnel.remove(getMainAdministrator());
        dao.remove(personnel);
    }

    /**
     * Возвращает объект DAO для работы основных методов доступа к базе данных,
     * реализованых в родительском классе {@link AbstractServiceImpl}.
     *
     * @return Объект класса {@link UserDAO} - объект DAO.
     * @throws BadRequestException Бросает исключение, если объект DAO равный null.
     */
    @Override
    public UserDAO getDao() throws BadRequestException {
        if (dao == null) {
            throw new BadRequestException("Can't find user DAO!");
        }
        return dao;
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     * Реализованый метод интерфейса {@link UserDetailsService}.
     *
     * @param username Логин пользователя для возврата
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws UsernameNotFoundException Бросает исключеник, если пользователь с логином username не найден.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
