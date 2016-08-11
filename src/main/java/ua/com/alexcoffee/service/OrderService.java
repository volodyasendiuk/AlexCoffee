package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Order;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса{@link Order}. Расширяет интерфейс {@link AbstractService}.
 *
 * @author Yurii Salimov
 * @see Order
 * @see AbstractService
 * @see ua.com.alexcoffee.service.impl.OrderServiceImpl
 */
public interface OrderService extends AbstractService<Order> {
    /**
     * Возвращает заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером
     * для возвращения.
     */
    Order get(String number);

    /**
     * Удаляет заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void remove(String number);
}
