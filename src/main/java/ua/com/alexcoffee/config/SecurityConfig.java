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
@ComponentScan(basePackages = {"ua.com.alexcoffee.service", "ua.com.alexcoffee.dao"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
     * ограниченным доступом, ограничение задано по ролям.К страницам, URL которых начинается
     * на "/admin/**", имеют доступ только пользователи с ролью - администратор. К страницам, URL
     * которых начинается на "/manager/**", имеют доступ администраторы и менеджера. Чтобы попасть на
     * эти страницы, нужно пройти этам авторизации.
     *
     * @param httpSecurity Объект класса HttpSecurity для настройки прав доступа к страницам.
     * @throws Exception Исключение методов класса HttpSecurity.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(this.roleService.getAdministrator().getTitle().name())
                .antMatchers("/manager/**").hasAnyRole(roleService.getAdministrator().getTitle().name(), roleService.getManager().getTitle().name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", false)
                .and()
                .exceptionHandling().accessDeniedPage("/forbidden_exception").and()
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
                .withUser("kexibqflvby").password("fktrrjaat").roles(roleService.getAdministrator().getTitle().name());
    }
}
