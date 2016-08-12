package ua.com.alexcoffee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Класс конфигурации Spring компонентов представления, настройка Mvc.
 * Указывает Spring где находятся компоненты представления, и как их отображать.
 * Помечен аннотацией @Configuration - класс является источником определения бинов;
 * аннотацией @EnableWebMvc - разрешает проекту использовать MVC;
 * аннотацией @ComponentScan - указываем фреймворку Spring, что компоненты надо искать внутри
 * пакетах "ua.com.alexcoffee.controller" и "ua.com.alexcoffee.config".
 *
 * @author Yurii Salimov
 * @see AppInitializer
 * @see RootConfig
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ua.com.alexcoffee.controller", "ua.com.alexcoffee.config"})
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Указывает Spring'у где находятся компоненты представления, и как их отображать.
     * Вьюшки будут лежать в директории /WEB-INF/views/ и иметь разширение *.jsp.
     *
     * @return Реализация интерфейса ViewResolver с настройками для вьюшек.
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    /**
     * Указывает где будут хранится ресурсы.
     *
     * @param resource Объект класса ResourceHandlerRegistry с настройками для ресурсов.
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry resource) {
        resource.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // Login controller

    /**
     * Настройка логин-контроллера.
     * Оказывает помощь в регистрации простого автоматизированного логин-контроллера предварительно
     * сконфигурированных с кодом состояния и вьюшкой.
     *
     * @param viewController Объект класса ViewControllerRegistry.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry viewController) {
        viewController.addViewController("/login").setViewName("login");
        viewController.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
