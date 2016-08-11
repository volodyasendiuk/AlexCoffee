package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.UserDAO;
import ua.com.alexcoffee.repository.RoleRepository;
import ua.com.alexcoffee.repository.UserRepository;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Класс реализует методы доступа объектов класса {@link User}
 * в базе данных интерфейса {@link UserDAO}, наследует родительский
 * абстрактній класс {@link AbstractDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link UserRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see AbstractDAOImpl
 * @see UserDAO
 * @see User
 * @see UserRepository
 */
@Repository
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {
    /**
     * Объект репозитория для работы с БД.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать репозиторий.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Объект репозитория для работы с БД.
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь.
     */
    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Возвращает пользователя из базы даных, у которого совпадает уникальный
     * логин с значением входящего параметра.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с уникальным логином.
     */
    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Возвращает главного администратора сайта.
     *
     * @return Объект класса {@link User} - главный администратор.
     */
    @Override
    public User getMainAdministrator() {
        Role admin = roleRepository.findOne((long) 2);
        return userRepository.findAllByRole(admin).get(0);
    }

    /**
     * Возвращает список всех администраторов сайта.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    @Override
    public List<User> getAdministrators() {
        Role admin = roleRepository.findOne((long) 2);
        return userRepository.findAllByRole(admin);
    }

    /**
     * Возвращает список всех менеджеров сайта.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    @Override
    public List<User> getManagers() {
        Role manager = roleRepository.findOne((long) 3);
        return userRepository.findAllByRole(manager);
    }

    /**
     * Возвращает список всех клиентов сайта.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    @Override
    public List<User> getClients() {
        Role client = roleRepository.findOne((long) 1);
        return userRepository.findAllByRole(client);
    }

    /**
     * Возвращает авторизированого пользователя.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    @Override
    public User getAuthenticatedUser() {
        User user;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            user = new User();
        }
        return user;
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    @Override
    public void remove(String name) {
        userRepository.deleteByName(name);
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     */
    @Override
    public void remove(Role role) {
        userRepository.deleteAllByRole(role);
    }

    /**
     * Возвращает объект репозитория для работы основных методов доступа к базе данных.
     *
     * @return Объект класса {@link UserRepository} - репозиторий.
     */
    @Override
    public UserRepository getRepository() {
        return userRepository;
    }
}
