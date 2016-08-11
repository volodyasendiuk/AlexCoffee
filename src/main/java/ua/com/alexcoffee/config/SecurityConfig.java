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

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ua.com.alexcoffee"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    private RoleService roleService;

    // Setting security rules
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                // Pages with URL ".../admin" to users-admin.
                .antMatchers("/admin/**").hasRole(this.roleService.getAdministrator().getTitle().name())
                // Pages with URL ".../manager" to users-manager.
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

    // Users configurations
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                // Users loaded from database
                .userDetailsService(userDetailsService).and()
                // Reserve user-admin in memory
                .inMemoryAuthentication()
                .withUser("kexibqflvby").password("fktrrjaat").roles(roleService.getAdministrator().getTitle().name());
    }
}
