package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.OrderDAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.service.OrderService;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Order}
 * в базе данных интерфейса {@link OrderService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see MainServiceImpl
 * @see OrderService
 * @see Order
 * @see OrderDAO
 */
@Service
public class OrderServiceImpl extends MainServiceImpl<Order> implements OrderService {
    /**
     * Реализация интерфейса {@link OrderDAO} для работы заказов с базой данных.
     */
    private OrderDAO dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param dao Реализация интерфейса {@link OrderDAO} для работы категорий с базой данных.
     */
    @Autowired
    public OrderServiceImpl(OrderDAO dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра. Режим только для чтения.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр number.
     * @throws BadRequestException       Бросает исключение, если не найден заказ с входящим параметром number.
     */
    @Override
    @Transactional(readOnly = true)
    public Order get(String number) throws WrongInformationException, BadRequestException {
        if (number == null || number.isEmpty()) {
            throw new WrongInformationException("No order number!");
        }

        Order order;

            order = dao.get(number);
        if (order == null) {
            throw new BadRequestException("Can't find order by number " + number + "!");
        }

        return order;
    }

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр number.
     */
    @Override
    @Transactional
    public void remove(String number) throws WrongInformationException {
        if (number == null || number.isEmpty()) {
            throw new WrongInformationException("No order number!");
        }
        dao.remove(number);
    }
}
