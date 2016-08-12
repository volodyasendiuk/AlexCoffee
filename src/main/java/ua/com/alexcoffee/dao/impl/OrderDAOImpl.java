package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.OrderDAO;
import ua.com.alexcoffee.repository.OrderRepository;
import ua.com.alexcoffee.model.Order;

/**
 * Класс реализует методы доступа объектов класса {@link Order}
 * в базе данных интерфейса {@link OrderDAO}, наследует родительский
 * абстрактній класс {@link MainDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link OrderRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see MainDAOImpl
 * @see OrderDAO
 * @see Order
 * @see OrderRepository
 */
@Repository
public class OrderDAOImpl extends MainDAOImpl<Order> implements OrderDAO {
    /**
     * Объект репозитория {@link OrderRepository} для работы категорий с базой данных.
     */
    private OrderRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Объект интерфейса {@link OrderRepository} для работы категорий с базой данных.
     */
    @Autowired
    public OrderDAOImpl(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером
     * для возвращения.
     */
    @Override
    public Order get(String number) {
        return repository.findByNumber(number);
    }

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    @Override
    public void remove(String number) {
        repository.deleteByNumber(number);
    }
}
