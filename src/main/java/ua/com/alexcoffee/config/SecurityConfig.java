package ua.com.alexcoffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.alexcoffee.service.RoleService;

/**
 * Класс настройки безопасности Spring Security. Класс расширяет класс WebSecurityConfigurerAdapter.
 * Аннотация @EnableWebSecurity в связке с WebSecurityConfigurerAdapter классом работает над обеспечением
 * аутентификации. Помечен аннотацией @ComponentScan - указываем фреймворку Spring, что компоненты надо
 * искать внутри пакетов "ua.com.alexcoffee.service" и "ua.com.alexcoffee.dao".
 *
 * @author Yurii Salimov
 * @see UserDetailsService
 * @see RoleService
 * @see ua.com.alexcoffee.model.User
 * @see ua.com.alexcoffee.model.Role
 * @see SecurityInitializer
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ua.com.alexcoffee.service", "ua.com.alexcoffee.dao", "ua.com.alexcoffee.repository"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Префикс URL запросов для администраторов.
     */
    private static final String ADMIN_REQUEST_URl = "/admin/**";

    /**
     * Префикс URL запросов для менеджеров.
     */
    private static final String MANAGER_REQUEST_URl = "/manager/**";

    /**
     * URL запроса для авторизации.
     */
    private static final String LOGIN_URL = "/login";

    /**
     * Название импута username на странице авторизации.
     */
    private static final String USERNAME = "username";

    /**
     * Название импута password на странице авторизации.
     */
    private static final String PASSWORD = "password";

    /**
     * URL запроса при отказе в доступе при авторизации.
     */
    private static final String ACCESS_DENIED_PAGE = "/forbidden_exception";

    /**
     * Логин запасного аккаунта.
     */
    private static final String DEFAULT_LOGIN = "adminka";

    /**
     * Пароль запасного аккаунта.
     */
    private static final String DEFAULT_PASSWORD = "vitaminka";

    /**
     * Объект сервиса для работы с зарегистрированными пользователями.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    public UserDetailsService userDetailsService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     * Поле помечано аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Настройка правил доступа пользователей к страницам сайта. Указываем адреса ресурсов с
     * ограниченным доступом, ограничение задано по ролям. К страницам, URL которых начинается
     * на "{@value ADMIN_REQUEST_URl}", имеют доступ только пользователи с ролью - администратор.
     * К страницам, URL которых начинается на "{@value MANAGER_REQUEST_URl}", имеют доступ
     * администраторы и менеджера. Чтобы попасть на эти страницы, нужно пройти этам авторизации.
     *
     * @param httpSecurity Объект класса HttpSecurity для настройки прав доступа к страницам.
     * @throws Exception Исключение методов класса HttpSecurity.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(ADMIN_REQUEST_URl)
                .hasRole(this.roleService.getAdministrator().getTitle().name())
                .antMatchers(MANAGER_REQUEST_URl)
                .hasAnyRole(roleService.getAdministrator().getTitle().name(), roleService.getManager().getTitle().name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage(LOGIN_URL)
                .usernameParameter(USERNAME)
                .passwordParameter(PASSWORD)
                .defaultSuccessUrl("/", false)
                .and()
                .exceptionHandling().accessDeniedPage(ACCESS_DENIED_PAGE).and()
                .csrf().disable();
    }

    /**
     * Настройка пользователей с их ролями. Пользователи будут подгружатся с базы данных,
     * используя реализацию методов интерфейса UserDetailsService. Также в памяти сохраняется
     * запись об резервном пользоватети с правами администратора.
     *
     * @param builder Объект класса AuthenticationManagerBuilder.
     * @throws Exception Исключение методов класса AuthenticationManagerBuilder.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userDetailsService).and()
                .inMemoryAuthentication()
                .withUser(DEFAULT_LOGIN).password(DEFAULT_PASSWORD)
                .roles(roleService.getAdministrator().getTitle().name());
    }
}
