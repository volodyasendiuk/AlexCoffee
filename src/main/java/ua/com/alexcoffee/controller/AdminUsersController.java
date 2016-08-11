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

@Controller
@RequestMapping(value = "/admin")
public class AdminUsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView viewAllPersonnel(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getPersonnel());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/user/all");
        return modelAndView;
    }

    @RequestMapping(value = "/view_user_{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("/admin/user/one");
        return modelAndView;
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public ModelAndView getAddUserPage(ModelAndView modelAndView) {
        modelAndView.addObject("roles", roleService.getPersonnel());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("/admin/user/add");
        return modelAndView;
    }

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

    @RequestMapping(value = "/save_user", method = RequestMethod.GET)
    public void saveUser() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/save_user\" is not supported!");
    }

    @RequestMapping(value = "/edit_user_{id}", method = RequestMethod.GET)
    public ModelAndView getEditUserPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("roles", roleService.getPersonnel());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("/admin/user/edit");
        return modelAndView;
    }

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

    @RequestMapping(value = "/update_user", method = RequestMethod.GET)
    public void updateUser() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_user\" is not supported!");
    }

    @RequestMapping(value = "/delete_user_{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        if (userService.getMainAdministrator().getId() != id) {
            userService.remove(id);
        }
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }

    @RequestMapping(value = "/delete_all_users", method = RequestMethod.GET)
    public ModelAndView deleteAll(ModelAndView modelAndView) {
        userService.removePersonnel();
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }
}
