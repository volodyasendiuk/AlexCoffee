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

@Controller
public class SEOController {

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

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    // Producing robots.txt file for search engines
    @RequestMapping(value = "/robots.txt", produces = {"text/plain"})
    @ResponseBody
    public String getRobotsTxt() {
        return ROBOTS;
    }

    // Producing sitemap.xml file for search engines
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

        List<Category> categories = categoryService.getAll();
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://alexcoffee.com.ua/category_").append(category.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

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
