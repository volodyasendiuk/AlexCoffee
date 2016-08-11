package ua.com.alexcoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.ForbiddenException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.*;
import ua.com.alexcoffee.service.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Класс-контроллер домашних страниц. К даному контроллеру и соответствующим
 * страницам могут обращатсья все пользователи, независимо от ихних ролей.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov
 * @see Product
 * @see Category
 * @see Order
 * @see ShoppingCart
 * @see ProductService
 * @see CategoryService
 * @see OrderService
 * @see ShoppingCartService
 * @see SenderService
 */
@Controller
public class HomeController {
    /**
     * Объект сервиса для работы с товарами.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private ProductService productService;

    /**
     * Объект сервиса для работы с категориями товаров.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Объект сервиса для работы с торговой корзиной.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Объект сервиса для работы с заказами.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Объект сервиса для работы с статусами заказов.
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
     * Объект сервиса для работы с товарами.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private SenderService senderService;

    /**
     * Перенаправляет запрос "/index" на запрос "/".
     * URL запроса "/index", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    /**
     * Возвращает главную cтраницу сайта "client/home". Для формирования страницы с базы подгружаются
     * категории товаров, 12 рандомных товаров и количество товаров в корзине.
     * URL запроса "/", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("products", productService.getRandom(12));
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/home");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/category" с товарами, которые пренадлежат категории с url.
     * URL запроса "/category_{url}", метод GET.
     *
     * @param url          URL категории, товары которой нужно вернуть на странице.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/category_{url}", method = RequestMethod.GET)
    public ModelAndView viewProductsInCategory(@PathVariable("url") String url, ModelAndView modelAndView) {
        modelAndView.addObject("category", categoryService.get(url));
        modelAndView.addObject("products", productService.getByCategoryUrl(url));
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/category");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/products" с всема товарами.
     * URL запроса "/all_products", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/all_products", method = RequestMethod.GET)
    public ModelAndView viewAllProducts(ModelAndView modelAndView) {
        modelAndView.addObject("products", productService.getAll());
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/products");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/product" с 1-м товаром с уникальним URL, который
     * совпадает с входящим параметром url. URL запроса "/product_{url}", метод GET.
     * В запросе в параметре url можно передавать как URL так и артикль товара.
     *
     * @param url          URL или артикль товара, который нужно вернуть на страницу.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/product_{url}", method = RequestMethod.GET)
    public ModelAndView viewProduct(@PathVariable("url") String url, ModelAndView modelAndView) {
        Product product;

        try {
            int article = Integer.parseInt(url);
            product = productService.getByArticle(article);
        } catch (NumberFormatException ex) {
            product = productService.getByUrl(url);
        }

        long categoryId = product.getCategory().getId();

        modelAndView.addObject("product", product);
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.addObject("featured_products", productService.getRandomByCategoryId(4, categoryId, product.getId()));
        modelAndView.setViewName("client/product");
        return modelAndView;
    }

    /**
     * Возвращает страницу "client/cart" - страница корзины с торговыми позициями, которие сделал клиент.
     * URL запроса "/cart", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView viewCart(ModelAndView modelAndView) {
        modelAndView.addObject("sales", shoppingCartService.getSalePositions());
        modelAndView.addObject("price_of_cart", shoppingCartService.getPrice());
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.setViewName("client/cart");
        return modelAndView;
    }

    /**
     * Добавляет товар с уникальным кодом id в корзину и перенаправляет по запросу "/cart".
     * URL запроса "/cart_add", метод POST.
     *
     * @param id           Уникальный код товара, который будет дбавлен в корзину.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/cart_add", method = RequestMethod.POST)
    public ModelAndView addProductToCart(@RequestParam long id, ModelAndView modelAndView) {
        SalePosition salePosition = new SalePosition(productService.get(id), 1);
        shoppingCartService.add(salePosition);
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/cart_add" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/cart_add", method = RequestMethod.GET)
    public void addProductToCart() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/cart_add\" is not supported!");
    }

    /**
     * Быстрое добавления товара с уникальным номером id в корзину и перенаправление
     * по запросу входящего параметра url. URL запроса "/cart_add_quickly", метод POST.
     *
     * @param id           Код товара, который нужно добавить в корзину.
     * @param url          Название (адрес) метода для перенаправления.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/cart_add_quickly", method = RequestMethod.POST)
    public ModelAndView addProductToCartQuickly(@RequestParam long id,
                                                @RequestParam String url,
                                                ModelAndView modelAndView) {
        SalePosition salePosition = new SalePosition(productService.get(id), 1);
        shoppingCartService.add(salePosition);
        modelAndView.setViewName("redirect:" + url);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/cart_add_quickly" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/cart_add_quickly", method = RequestMethod.GET)
    public void addProductToCartQuickly() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/cart_add_quickly\" is not supported!");
    }

    /**
     * Очищает корзину от торгвых позиции и перенаправление по запросу "/cart".
     * URL запроса "/cart_clear", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/cart_clear", method = RequestMethod.GET)
    public ModelAndView clearCart(ModelAndView modelAndView) {
        shoppingCartService.clear();
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    /**
     * Оформляет и сохраняет заказ клиента, возвращает страницу "client/checkout".
     * URL запроса "/checkout", метод POST.
     *
     * @param name         Имя клиента, сжелавшего заказ.
     * @param email        Электронная почта клиента.
     * @param phone        Номер телефона клиента.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView viewCheckout(@RequestParam(value = "user_name") String name,
                                     @RequestParam(value = "user_email") String email,
                                     @RequestParam(value = "user_phone") String phone,
                                     ModelAndView modelAndView) {
        Role role = roleService.getDefault();
        User client = new User(name, email, phone, role);

        Status status = statusService.getDefault();
        Order order = new Order(new Date(), status, client);
        order.setSalePositions(new ArrayList<>(shoppingCartService.getSalePositions()));
        orderService.add(order);

        senderService.send(order);

        modelAndView.addObject("order", order);
        modelAndView.addObject("sales", order.getSalePositions());
        modelAndView.addObject("price_of_cart", shoppingCartService.getPrice());
        modelAndView.addObject("cart_size", 0);
        modelAndView.setViewName("client/checkout");

        shoppingCartService.clear();

        return modelAndView;
    }

    /**
     * Перенаправляет по запросу "/cart", если обратится по запросу "/checkout" методом GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView viewCheckout(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    /**
     * Возвращает исключение ForbiddenException, если пользователь обращается к запросам, к которым
     * он не имеет права доступа (роли).
     *
     * @throws ForbiddenException Бросает исключение в случае отсутствия прав доступа.
     */
    @RequestMapping(value = "/forbidden_exception", method = RequestMethod.GET)
    public void getForbiddenException() throws ForbiddenException {
        throw new ForbiddenException("You do not have sufficient permissions to access this page.");
    }
}
