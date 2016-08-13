package ua.com.alexcoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.service.OrderService;
import ua.com.alexcoffee.service.RoleService;
import ua.com.alexcoffee.service.StatusService;
import ua.com.alexcoffee.service.UserService;

import java.util.Date;

/**
 * Класс-контроллер страниц, предназначеных для менеджеров. К даному контроллеру и соответствующим
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
public class ManagerController {
    /**
     * Объект сервиса для работы с пользователями.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private UserService userService;

    /**
     * Объект сервиса для работы с заказами клиентов.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Объект сервиса для работы с статусами виполнения заказов.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private StatusService statusService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "manager/order/all".
     * URL запроса {"/manager" , "/manager/orders"}, метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = {"", "/orders"}, method = RequestMethod.GET)
    public ModelAndView viewAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", orderService.getAll());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("manager/order/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "manager/order/one".
     * URL запроса "/manager/view_order_{id}", метод GET.
     *
     * @param id           Код заказа, который нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/view_order_{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sales", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", statusService.getDefault());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.setViewName("manager/order/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit" для редактирование заказа с уникальным кодом,
     * который совпадает с параметром id, или перенаправляет по запросу "/manager/orders", если
     * этот заказ уже обработал другой менеджер. URL запроса "/admin/edit_order_{id}", метод GET.
     *
     * @param id           Код заказа, которую нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/edit_order_{id}", method = RequestMethod.GET)
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        if (order.getManager() == null || order.getManager().equals(userService.getAuthenticatedUser())) {
            modelAndView.addObject("order", order);
            modelAndView.addObject("sales", order.getSalePositions());
            modelAndView.addObject("order_price", order.getPrice());
            modelAndView.addObject("statuses", statusService.getAll());
            modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
            modelAndView.setViewName("manager/order/edit");
        } else {
            modelAndView.setViewName("redirect:/manager/orders");
        }
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/admin/view_order_{id}".
     * URL запроса "/admin/update_category", метод POST.
     *
     * @param id              Код заказа для обновления.
     * @param managerId       Код менеджера или администратора, который обработал заказ в последний раз.
     * @param number          Номер заказа.
     * @param statusId        Код статуса выполнения заказа.
     * @param name            Имя клиента, оформивший заказ.
     * @param email           Электронная почта клиента.
     * @param phone           Номер телефона клиента.
     * @param shippingAddress Адрес доставки товаров заказа.
     * @param shippingDetails Детали доставки заказа.
     * @param description     Описание заказа.
     * @param modelAndView    Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.POST)
    public ModelAndView updateOrder(@RequestParam long id,
                                    @RequestParam(value = "auth_user") long managerId,
                                    @RequestParam String number,
                                    @RequestParam(value = "status") long statusId,
                                    @RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String phone,
                                    @RequestParam(value = "shipping-address") String shippingAddress,
                                    @RequestParam(value = "shipping-details") String shippingDetails,
                                    @RequestParam String description,
                                    ModelAndView modelAndView) {
        Order order = orderService.get(id);

        if (order.getManager() == null || order.getManager() == userService.getAuthenticatedUser()) {

            User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);

            Status status = statusService.get(statusId);
            User manager = null;
            if (!status.equals(statusService.getDefault())) {
                manager = userService.get(managerId);
            }

            order.initializer(number, new Date(), shippingAddress, shippingDetails, description, status, client, manager);
            orderService.update(order);
        }

        modelAndView.setViewName("redirect:/manager/view_order_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/update_order" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.GET)
    public void updateOrder() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_order\" is not supported!");
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
        modelAndView.setViewName("manager/user/one");
        return modelAndView;
    }
}
