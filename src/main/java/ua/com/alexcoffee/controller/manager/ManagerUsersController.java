package ua.com.alexcoffee.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.service.OrderService;
import ua.com.alexcoffee.service.RoleService;
import ua.com.alexcoffee.service.UserService;

/**
 * Класс-контроллер страниц, предназначеных для управления пользователей менеджерами. К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи, имеющие роль-админстратор и -менеджер.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/manager") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/manager".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov
 * @see User
 * @see Order
 * @see UserService
 * @see OrderService
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerUsersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private UserService userService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     */
    private RoleService roleService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     *
     * @param userService Объект сервиса для работы с пользователями.
     * @param roleService Объект сервиса для работы с ролями пользователей.
     */
    @Autowired
    public ManagerUsersController(UserService userService, RoleService roleService) {
        super();
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Возвращает всех пользователей на страницу "manager/user/all".
     * URL запроса "/manager/users", метод GET.
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
        modelAndView.setViewName("manager/user/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя с уникальным кодом id на страницу "manager/user/one".
     * URL запроса "/manager/view_user_{id}", метод GET.
     *
     * @param id           Код пользвателя, которою нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/view_user_{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("manager/user/one");
        return modelAndView;
    }
}
