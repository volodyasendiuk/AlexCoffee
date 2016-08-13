package ua.com.alexcoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.RoleService;
import ua.com.alexcoffee.service.UserService;

/**
 * Класс-контроллер страниц управления персоналом сайта. К даному контроллеру и
 * соответствующим страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/admin".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov
 * @see User
 * @see UserService
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminUsersController {
    /**
     * Объект сервиса для работы с пользователями.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private UserService userService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Возвращает всех пользователей на страницу "admin/user/all".
     * URL запроса "/admin/users", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView viewAllPersonnel(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getPersonnel());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/user/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя с уникальным кодом id на страницу "admin/user/one".
     * URL запроса "/admin/view_user_{id}", метод GET.
     *
     * @param id           Код категории, которою нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/view_user_{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("/admin/user/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/user/add" для добавления нового пользователе, члена персонала
     * (администратора или менеджера). URL запроса "/admin/add_user", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public ModelAndView getAddUserPage(ModelAndView modelAndView) {
        modelAndView.addObject("roles", roleService.getPersonnel());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("/admin/user/add");
        return modelAndView;
    }

    /**
     * Сохраняет нового пользователя по входящим параметрам и перенаправляет по запросу "/admin/users".
     * URL запроса "/admin/save_user", метод POST.
     *
     * @param name         Имя нового пользователя.
     * @param roleId       Код роли пользователя.
     * @param username     Логин пользователя для входа в аккаунт на сайте.
     * @param password     Пароль пользователя для входа в аккаунт на сайте.
     * @param email        Электронная почта пользователя.
     * @param phone        Номер телефона пользователя.
     * @param vkontakte    Ссылка на страничку в соц. сети "ВКонтакте" пользователя.
     * @param facebook     Ссылка на страничку в соц. сети "Facebook" пользователя.
     * @param skype        Логин пользователя в месенджере "Skype".
     * @param description  Описание пользователя.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ModelAndView saveUser(@RequestParam String name,
                                 @RequestParam(value = "role") long roleId,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String vkontakte,
                                 @RequestParam String facebook,
                                 @RequestParam String skype,
                                 @RequestParam String description,
                                 ModelAndView modelAndView) {
        User user = new User();
        Role role = roleService.get(roleId);
        user.initializer(name, username, password, email, phone, vkontakte, facebook, skype, description, role);
        userService.add(user);

        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/save_user" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/save_user", method = RequestMethod.GET)
    public void saveUser() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/save_user\" is not supported!");
    }

    /**
     * Возвращает страницу "admin/user/edit" для редактирование пользователя с уникальным кодом,
     * который совпадает с параметром id. URL запроса "/admin/edit_user_{id}", метод GET.
     *
     * @param id           Код пользователя, информацию о котором нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/edit_user_{id}", method = RequestMethod.GET)
    public ModelAndView getEditUserPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("roles", roleService.getPersonnel());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("/admin/user/edit");
        return modelAndView;
    }

    /**
     * Обновляет пользователя по входящим параметрам и перенаправляет по запросу "/admin/view_user_{id}".
     * URL запроса "/admin/update_user", метод POST.
     *
     * @param id           Код пользователя для обновления.
     * @param name         Имя пользователя.
     * @param roleId       Код роли пользователя.
     * @param username     Логин пользователя для входа в аккаунт на сайте.
     * @param password     Пароль пользователя для входа в аккаунт на сайте.
     * @param email        Электронная почта пользователя.
     * @param phone        Номер телефона пользователя.
     * @param vkontakte    Ссылка на страничку в соц. сети "ВКонтакте" пользователя.
     * @param facebook     Ссылка на страничку в соц. сети "Facebook" пользователя.
     * @param skype        Логин пользователя в месенджере "Skype".
     * @param description  Описание пользователя.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam long id,
                                   @RequestParam String name,
                                   @RequestParam(value = "role") long roleId,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam String phone,
                                   @RequestParam String vkontakte,
                                   @RequestParam String facebook,
                                   @RequestParam String skype,
                                   @RequestParam String description,
                                   ModelAndView modelAndView) {
        User user = userService.get(id);
        Role role = roleService.get(roleId);
        user.initializer(name, username, password, email, phone, vkontakte, facebook, skype, description, role);
        userService.update(user);

        modelAndView.setViewName("redirect:/admin/view_user_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/update_user" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/update_user", method = RequestMethod.GET)
    public void updateUser() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_user\" is not supported!");
    }

    /**
     * Удаляет пользователя с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/users".
     * URL запроса "/delete_user_{id}", метод GET.
     *
     * @param id           Код пользвателя, которого нужно удалить.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/delete_user_{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        if (userService.getMainAdministrator().getId() != id) {
            userService.remove(id);
        }
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }

    /**
     * Удаляет всех пользователей и перенаправляет по запросу "/admin/users".
     * URL запроса "/delete_all_users", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/delete_all_users", method = RequestMethod.GET)
    public ModelAndView deleteAll(ModelAndView modelAndView) {
        userService.removePersonnel();
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }
}
