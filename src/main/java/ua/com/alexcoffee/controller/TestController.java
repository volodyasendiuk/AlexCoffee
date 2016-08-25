package ua.com.alexcoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.service.ShoppingCartService;

/**
 * Класс-контроллер тестовых страниц. К даному контроллеру и соответствующим
 * страницам могут обращатсья все пользователи, независимо от ихних ролей.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 */
@Controller
public class TestController {

    /**
     * Объект сервиса для работы с торговой корзиной.
     */
    private ShoppingCartService shoppingCartService;

    /**
     * Конструктор для инициализации основных переменных контроллера главных страниц сайта.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     *
     * @param shoppingCartService Объект сервиса для работы с торговой корзиной.
     */
    @Autowired
    public TestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Возвращает страцницу "client/test" с тестовым описанием сайта.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView getTestPage(ModelAndView modelAndView) {
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/test");
        return modelAndView;
    }
}
