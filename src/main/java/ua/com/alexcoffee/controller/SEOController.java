package ua.com.alexcoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.service.CategoryService;
import ua.com.alexcoffee.service.ProductService;

import java.util.List;

/**
 * Класс-контроллер для настройки поисковой оптимизации (SEO).
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 *
 * @author Yurii Salimov
 */
@Controller
public class SEOController {

    /**
     * Информация о сайте для поисковых систем.
     */
    private final static String ROBOTS = "User-agent: Yandex\n" +
            "Disallow: /admin\n" +
            "Disallow: /manager\n" +
            "Disallow: /login\n" +
            "Disallow: /resources\n" +
            "Host: alexcoffee.com.ua\n" +
            "\n" +
            "User-agent: Googlebot\n" +
            "Disallow: /admin\n" +
            "Disallow: /manager\n" +
            "Disallow: /login\n" +
            "Disallow: /resources\n" +
            "\n" +
            "User-agent: *\n" +
            "Crawl-delay: 30\n" +
            "Disallow: /admin\n" +
            "Disallow: /manager\n" +
            "Disallow: /login\n" +
            "Disallow: /resources\n" +
            "\n" +
            "Sitemap: http://alexcoffee.com.ua/sitemap.xml";

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
     * Возвращает файл robots.txt для поисковых систем.
     * Аннотация @ResponseBody указывает на то, что результат работы метода в контроллере был выведен
     * непосредственно в тело ответа на запрос, а не послужил адресом перехода и не был помещён как параметр в модель.
     *
     * @return Значение типа String - информация о сайте для поисковых систем.
     */
    @RequestMapping(value = "/robots.txt", produces = {"text/plain"})
    @ResponseBody
    public String getRobotsTxt() {
        return ROBOTS;
    }

    /**
     * Возвращает файл sitemap.xml для поисковых систем.
     * Аннотация @ResponseBody указывает на то, что результат работы метода в контроллере был выведен
     * непосредственно в тело ответа на запрос, а не послужил адресом перехода и не был помещён как параметр в модель.
     *
     * @return Значение типа String - информация о ссылках на сайте для поисковых систем.
     */
    @RequestMapping(value = "/sitemap.xml", produces = {"application/xml"})
    @ResponseBody
    public String getSiteMapXml() {
        StringBuilder sitemap = new StringBuilder();
        sitemap.append("<urlset\n")
                .append("      xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n")
                .append("      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n")
                .append("      xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9\n")
                .append("            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\n")
                .append("<url>\n")
                .append("  <loc>http://alexcoffee.com.ua/</loc>\n")
                .append("</url>\n");

        // Ссылки на категории товаров.
        List<Category> categories = categoryService.getAll();
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://alexcoffee.com.ua/category_").append(category.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

        // Ссылки на товары.
        List<Product> products = productService.getAll();
        if (!products.isEmpty()) {
            sitemap.append("<url>\n")
                    .append("  <loc>http://alexcoffee.com.ua/all_products</loc>\n")
                    .append("</url>\n");

            for (Product product : products) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://alexcoffee.com.ua/product_").append(product.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

        sitemap.append("</urlset>");

        return sitemap.toString();
    }
}
