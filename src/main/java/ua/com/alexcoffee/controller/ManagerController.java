package ua.com.alexcoffee.controller;

import ua.com.alexcoffee.entity.Order;
import ua.com.alexcoffee.entity.Status;
import ua.com.alexcoffee.entity.User;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.service.OrderService;
import ua.com.alexcoffee.service.RoleService;
import ua.com.alexcoffee.service.StatusService;
import ua.com.alexcoffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView manager(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/manager/orders");
        return modelAndView;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView viewAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", orderService.getAll());
        modelAndView.setViewName("manager/order/all");
        return modelAndView;
    }

    @RequestMapping(value = "/view_order_{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("products", order.getProducts());
        modelAndView.addObject("priceOfAllProducts", orderService.getPriceOfProducts(order));
        modelAndView.setViewName("manager/order/one");
        return modelAndView;
    }

    @RequestMapping(value = "/edit_order_{id}", method = RequestMethod.GET)
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("products", order.getProducts());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("priceOfAllProducts", orderService.getPriceOfProducts(order));
        modelAndView.setViewName("manager/order/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/update_order", method = RequestMethod.POST)
    public ModelAndView updateOrder(@RequestParam long id,
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

        User client = order.getClient();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        Status status = statusService.get(statusId);
        order.setAllInfo(number, new Date(), shippingAddress, shippingDetails, description, status, client);

        orderService.update(order);

        modelAndView.setViewName("redirect:/manager/view_order_" + id);
        return modelAndView;
    }

    @RequestMapping(value = "/update_order", method = RequestMethod.GET)
    public ModelAndView updateOrder() throws BadRequestException {
        throw new BadRequestException("GET method in \"/update_order\" is not supported!");
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView viewAllPersonnel(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getPersonnel());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.setViewName("manager/user/all");
        return modelAndView;
    }

    @RequestMapping(value = "/view_user_{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.setViewName("manager/user/one");
        return modelAndView;
    }
}
