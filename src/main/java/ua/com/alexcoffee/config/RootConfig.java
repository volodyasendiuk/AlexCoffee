package ua.com.alexcoffee.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Класс основных конфигураций для Spring: DataSource, JpaVendorAdapter, JpaTransactionManager,
 * BeanPostProcessor, CommonsMultipartResolver.
 * Помечен аннотацией @Configuration - класс является источником определения бинов;
 * аннотацией @EnableTransactionManagement - активирует возможности Spring транзакции через @Transactional;
 * аннотацией @EnableJpaRepositories - активирует Spring Data JPA, который будет создавать конкретную
 * реализацию для репозитория из пакета "ua.com.alexcoffee.repository" и настраивать на взаимодействие
 * с БД в памяти, используя JPA;
 * аннотацией @ComponentScan - указываем фреймворку Spring, что компоненты надо искать внутри
 * пакета "ua.com.alexcoffee.model".
 *
 * @author Yurii Salimov
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"ua.com.alexcoffee.repository"})
@ComponentScan(basePackages = {"ua.com.alexcoffee.model"})
public class RootConfig {

    /**
     * Возвращает объект класса DataSource с настройками подключения к базе данных.
     * Нужен для получения физического соединения с базой данных.
     *
     * @return Объект класса DataSource - настройки для базы данных.
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/alexcoffee?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Драйвер для подключение к базе данных.
        basicDataSource.setUsername("root"); // Логин для подключение к базе данных.
        basicDataSource.setPassword("admin"); // Пароль для подключение к базе данных.
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxActive(20);
        return basicDataSource;
    }

    /**
     * Возвращает настройки адаптера (JPA provider) для подключения к базе данных.
     *
     * @return Объект класса HibernateJpaVendorAdapter - адаптера для подключения к базе данных..
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(false);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    /**
     * Создает фабрику EntityManager, может быть передана в DAO, JPA с помощью инъекции зависимостей.
     *
     * @param dataSource       Объект класса DataSource с настройками подключения к базе данных.
     * @param jpaVendorAdapter Реализация интерфейса JpaVendorAdapter - адаптера для подключения к базе данных.
     * @return Объект класса LocalContainerEntityManagerFactoryBean.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcemfb.setPackagesToScan("ua.com.alexcoffee.model");
        return lcemfb;
    }

    /**
     * Возвращает менеджера транзакций, который  подходит для приложений, использующих единую
     * JPA EntityManagerFactory для транзакционного доступа к данным.
     *
     * @param factory Реализация интерфейса EntityManagerFactory.
     * @return Объект класса JpaTransactionManager с входящей фабрикой ентети менеджера factory.
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    /**
     * Переводит (перехватывает) любые JPA или Hibernate исключения в Spring исключения.
     *
     * @return Реализация интерфейса PersistenceExceptionTranslationPostProcessor.
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Возвращает объект класса CommonsMultipartResolver, который сохраняет временные файлы
     * во временный каталог сервлет контейнера.
     *
     * @return Объект класса CommonsMultipartResolver для временного сохранения файлов.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}
